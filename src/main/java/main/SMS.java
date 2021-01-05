package main;

import br.com.totalvoice.TotalVoiceClient;
import br.com.totalvoice.api.Sms;

import java.time.LocalDateTime;

public class SMS {

    SMS() { }

    SMS(String mensagem) {
        try {
            TotalVoiceClient client = new TotalVoiceClient("5f4c6badeda6bad11b306d58b237c89f");
            Sms sms = new Sms(client);
            LocalDateTime agora = LocalDateTime.now();
            String mensagemCustomizada = "Resposta Servidor: " + mensagem + " " + " :: Hora de registro do PC: " + agora.getHour() + ":" + agora.getMinute();

            sms.enviar("62992412741", mensagemCustomizada);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
