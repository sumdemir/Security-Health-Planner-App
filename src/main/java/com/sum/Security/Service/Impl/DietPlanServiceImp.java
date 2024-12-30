package com.sum.Security.Service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sum.Security.AIresponse.DietPlan;
import com.sum.Security.Service.DietPlanService;
import com.sum.Security.repository.ClientRepository;
import com.sum.Security.repository.DietPlanRepository;
import com.sum.Security.repository.DietitianRepository;
import com.sum.Security.user.Client;
import com.sum.Security.user.Dietitian;
import lombok.AllArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class DietPlanServiceImp implements DietPlanService{

    private final ClientRepository clientRepository;
    private final DietPlanRepository dietPlanRepository;
    private final DietitianRepository dietitianRepository;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String API_KEY = "xxx";
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + API_KEY;

    @Override
    public String getDietPlan(Integer clientId, Integer dietitianId) {

        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
        Dietitian dietitian = dietitianRepository.findById(dietitianId).orElseThrow(() -> new RuntimeException("Dietitian not found"));

        String dietPlanResponse = requestDietPlan(client);
        JSONObject jsonResponse = new JSONObject(dietPlanResponse);
        JSONArray candidates = jsonResponse.getJSONArray("candidates");
        JSONObject content = candidates.getJSONObject(0).getJSONObject("content");
        JSONArray parts = content.getJSONArray("parts");
        String text = parts.getJSONObject(0).getString("text");

        DietPlan dietPlan = new DietPlan();
        dietPlan.setPlanDetails(text);
        dietPlan.setPlanName("Diet Plan for " + client.getFirstname());
        dietPlan.setClient(client);
        dietPlan.setDietitian(dietitian);
        dietPlan.setCreatedAt(new Timestamp(System.currentTimeMillis()));


        dietPlanRepository.save(dietPlan);

        return dietPlanResponse;
    }

    @Override
    public String getDietPlanChat(Integer clientId, Integer dietitianId) {

        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
        Dietitian dietitian = dietitianRepository.findById(dietitianId).orElseThrow(() -> new RuntimeException("Dietitian not found"));

        String dietPlanResponse = requestDietPlan(client);
        JSONObject jsonResponse = new JSONObject(dietPlanResponse);
        JSONArray candidates = jsonResponse.getJSONArray("candidates");
        JSONObject content = candidates.getJSONObject(0).getJSONObject("content");
        JSONArray parts = content.getJSONArray("parts");
        String text = parts.getJSONObject(0).getString("text");

        DietPlan dietPlan = new DietPlan();
        dietPlan.setPlanDetails(text);
        dietPlan.setPlanName("Diet Plan for " + client.getFirstname());
        dietPlan.setClient(client);
        dietPlan.setDietitian(dietitian);
        dietPlan.setCreatedAt(new Timestamp(System.currentTimeMillis()));


        dietPlanRepository.save(dietPlan);

        return dietPlanResponse;
    }

    public String requestDietPlan(Client client) {
        try {
            String prompt = createDietPlanPrompt(client);

            String requestBody = objectMapper.writeValueAsString(Map.of(
                    "contents", List.of(
                            Map.of("parts", List.of(Map.of("text", prompt)))
                    )
            ));

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/json");

            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);

            String responseString = response.getBody();
            return response.getBody();
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Error occurred while requesting diet plan.";
        }
    }

    private String createDietPlanPrompt(Client client) {
        return String.format(
                "Sen sana gelen müşterilere haftalık yeme içme programı oluşturan bir sanal diyetisyensin. Ona göre konuş ve program oluştur. "
                        + "Müşterinin verdiği bilgilere göre günlük alması gereken kaloriyi hesapla ve ona göre haftalık diyet listesi hazırla. "
                        + "Bana aşağıdaki bilgileri vererek, kişiye özel bir diyet planı oluştur: Yaş: %d, Boy: %.2f cm, Kilo: %.2f kg, "
                        + "Tıbbi Durumlar: %s, Hedef Kilo: %s, Aktivite Seviyesi: %s. "
                        + "Diyet planını 1 hafta boyunca, her gün için kahvaltı, öğle yemeği, akşam yemeği ve atıştırmalıklar dahil olacak şekilde hazırla. "
                        + "Her öğün için kalori miktarını belirt ve makro besin değerlerini (protein, karbonhidrat, yağ) dengeli bir şekilde paylaştır. "
                        + "Diyet planı, sağlıklı ve sürdürülebilir bir kilo kaybı sağlamayı hedeflemeli ve günlük müşterinin alması gerektiği kalori miktarı kadar olmalı. "
                        + "Yemekler, tam gıdalar, porsiyon kontrolü ve dengeli beslenmeye odaklanmalı. "
                        + "Atıştırmalıklar da dahil olmak üzere, tüm öğünler pratik ve uygulanabilir olmalı, böylece müşteri bu planı günlük yaşamına kolayca adapte edebilir. "
                        + "Ayrıca, bol su içme ve düzenli öğün saatlerine dikkat etmenin önemini vurgula. "
                        + "Hedef, sağlıklı bir şekilde kilo kaybı sağlarken genel sağlığı iyileştirmek ve dengeli bir yaşam tarzını sürdürmek olmalı."
                        + "\n\nPlanı, hedef kiloya ulaşmaya yönelik uygun, sürdürülebilir bir diyet önerisiyle oluştur.",
                client.getAge(),
                client.getHeight(),
                client.getWeight(),
                client.getMedicalConditions(),
                client.getGoal(),
                client.getActivityLevel()
        );
    }
}
