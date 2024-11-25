package com.sum.Security.Service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sum.Security.AIresponse.TrainingPlan;
import com.sum.Security.Service.TrainingPlanService;
import com.sum.Security.repository.ClientRepository;
import com.sum.Security.repository.TrainerRepository;
import com.sum.Security.repository.TrainingPlanRepository;
import com.sum.Security.user.Client;
import com.sum.Security.user.Trainer;
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
public class TrainingPlanServiceImp implements TrainingPlanService {

    private final ClientRepository clientRepository;
    private final TrainingPlanRepository trainingPlanRepository;
    private final TrainerRepository trainerRepository;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String API_KEY = "AIzaSyCxjiFflp8DlN0anjpmbwQN0qjJ_OOhpCA";
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + API_KEY;

    @Override
    public String getTrainingPlan(Integer clientId, Integer trainerId) {

        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
        Trainer trainer = trainerRepository.findById(trainerId) .orElseThrow(() -> new RuntimeException("Trainer not found."));

        String trainingPlanResponse = requestTrainingPlan(client);
        JSONObject jsonResponse = new JSONObject(trainingPlanResponse);
        JSONArray candidates = jsonResponse.getJSONArray("candidates");
        JSONObject content = candidates.getJSONObject(0).getJSONObject("content");
        JSONArray parts = content.getJSONArray("parts");
        String text = parts.getJSONObject(0).getString("text");

        TrainingPlan trainingPlan = new TrainingPlan();
        trainingPlan.setPlanDetails(text);
        trainingPlan.setPlanName("Training plan for" + client.getFirstname());
        trainingPlan.setClient(client);
        trainingPlan.setTrainer(trainer);
        trainingPlan.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        trainingPlanRepository.save(trainingPlan);

        return trainingPlanResponse;

    }

    private String requestTrainingPlan(Client client) {
        try {
            String prompt = createTrainingPlanPrompt(client);

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
            return "Error occurred while requesting training plan.";
        }
    }

    private String createTrainingPlanPrompt(Client client) {
        String workoutDays;

        switch (client.getActivityLevel()) {
            case LOW:
                workoutDays = "haftada 2 gün";
                break;
            case MEDIUM:
                workoutDays = "haftada 3 gün";
                break;
            case HARD:
                workoutDays = "haftada 5 gün";
                break;
            default:
                workoutDays = "belirtilen aktivite seviyesine uygun bir program";
                break;
        }

        return String.format(
                "Sen sana gelen müşterilere haftalık egzersiz programı oluşturan bir sanal antrenörsün. Ona göre konuş ve program oluştur. "
                        + "Müşterinin verdiği bilgilere göre ona özel bir egzersiz programı hazırla ve hedeflerine ulaşmasını sağla. "
                        + "Müşterinin sağlık durumlarını göz önünde bulundurmayı programı oluştururken atlama, dikkat et."
                        + "Müşterinin verdiği bilgiler: Yaş: %d, Boy: %.2f cm, Kilo: %.2f kg, "
                        + "Tıbbi Durumlar: %s, Hedef Kilo: %s, Aktivite Seviyesi: %s. "
                        + "Egzersiz programını %s olacak şekilde düzenle. "
                        + "Her gün için uygun egzersizleri öner ve programı detaylandır. Egzersizlerin kas gruplarını dengeli bir şekilde çalıştırmasını sağla. "
                        + "Program, müşterinin kondisyonunu artıracak, gücünü geliştirecek ve sağlıklı bir şekilde hedeflerine ulaşmasını destekleyecek şekilde olmalı. "
                        + "Egzersizler, düşük riskli ve güvenli olmalı, ayrıca ihtiyaç duyulan dinlenme sürelerine dikkat etmelidir. "
                        + "Her gün için ısınma, ana antrenman ve soğuma aşamalarını içeren bir program öner. Ayrıca, egzersizlerin uygulanabilir ve müşterinin günlük hayatına kolayca uyum sağlayacak şekilde olduğundan emin ol. "
                        + "Egzersizler, müşterinin kondisyonunu artırmak, genel sağlığını iyileştirmek ve dengeli bir yaşam tarzını sürdürmek için tasarlanmış olmalı. "
                        + "Önerdiğin her egzersiz için uygun set ve tekrar sayılarını da belirle. "
                        + "Örneğin, başlangıç seviyesi için her egzersiz için 3 set ve 10-15 tekrar önerilebilirken, ileri seviye için set ve tekrar sayıları artırılabilir. "
                        + "Programda hangi günler ağırlık antrenmanı, hangi günler kardiyo yapılacağına dikkat et. Dinlenme günlerini de plana ekle.",
                client.getAge(),
                client.getHeight(),
                client.getWeight(),
                client.getMedicalConditions(),
                client.getGoal(),
                client.getActivityLevel(),
                workoutDays
        );
    }
}
