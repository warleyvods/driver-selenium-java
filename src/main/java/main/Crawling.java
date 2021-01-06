package main;

import java.io.IOException;
import java.time.LocalDateTime;

import static main.Frequencia.registrarFrequencia;


public class Crawling {
    public static void main(String[] args) {


        String cpf = "03964879126";
        String senha = "Cpktnwt1";
        final ESTADO[] estado = {ESTADO.INICIO_EXPEDIENTE};


        Thread tr = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);

                    LocalDateTime now = LocalDateTime.now();
                    System.out.println(now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());

                    //entra no IF às 7:45h
                    if (now.getHour() == 7 && now.getMinute() == 45 && estado[0].equals(ESTADO.INICIO_EXPEDIENTE)) {

                        //Aguarda entre 0 e 20 minutos
                        Thread.sleep(getDelay(20));
                        new SMS("BATEU PONTO");

                        //Registra o ponto (início de expediente)
//                        registrarFrequencia(cpf, senha);


                        //altera o próximo passo para ALMOÇO
                        estado[0] = ESTADO.ALMOCO;

                        //entra no IF às 12:00h
                    } else if (now.getHour() == 12 && now.getMinute() == 0 && estado[0].equals(ESTADO.ALMOCO)) {
                        //Aguarda entre 0 e 5 minutos
                        Thread.sleep(getDelay(5));
                        new SMS("BATEU PONTO");
                        //registra o ponto (saída para o almoço)
//                        registrarFrequencia(cpf, senha);

                        //aguarda entre 1:00h e 1:05
                        Thread.sleep(getDelay(5) + (60 * 60 * 1000));

                        //registra o ponto (volta do almoço)
//                        registrarFrequencia(cpf, senha);
                        new SMS("BATEU PONTO");

                        //altera o próximo passo para FIM_EXPEDIENTE
                        estado[0] = ESTADO.FIM_EXPEDIENTE;

                        //entra no IF às 17:00
                    } else if (now.getHour() == 17 && now.getMinute() == 0 && estado[0].equals(ESTADO.FIM_EXPEDIENTE)) {

                        //aguarda entre 0 e 20 minutos
                        Thread.sleep(getDelay(20));

                        //registra o ponto (fim de expediente)
//                        registrarFrequencia(cpf, senha);
                        new SMS("BATEU PONTO");

                        //altera o próximo passo para INICIO_EXPEDIENTE
                        estado[0] = ESTADO.INICIO_EXPEDIENTE;

                    }


                } catch (InterruptedException ie) {
                    ie.getMessage();
                }
            }
        });

        //Essa validação abaixo verifica qual o ponto que vai começar sendo registrado.
        //Não funciona se o sistema for iniciado durante o horário de almoço.
        //Deve ser iniciado ou antes do ponto do almoço ser batido manualmente ou após ter retornado dele.
        //Fora isso, pode ser iniciado em qualquer horário

        int horaInicioExecucao = LocalDateTime.now().getHour();
        if (horaInicioExecucao >= 8 && horaInicioExecucao <= 12) {
            estado[0] = ESTADO.ALMOCO;
        } else if (horaInicioExecucao > 12 && horaInicioExecucao <= 17) {
            estado[0] = ESTADO.FIM_EXPEDIENTE;
        } else {
            estado[0] = ESTADO.INICIO_EXPEDIENTE;
        }

        tr.start();

    }


    private static int getDelay(int maximoMinutos) {
        //pega um numero randomico sempre menor que 1;
        double random = Math.random();

        //transforma o número para ficar entre 0 e o valor passado em 'maximoMinutos'
        random = random * maximoMinutos;

        //remove as casas decimais
        random = Math.floor(random);

        //transforma de minutos em segundos
        random = random * 60;

        //transforma de segundos para milisegundos
        random = random * 1000;

        //transforma de double para int
        int retorno = Double.valueOf(random).intValue();


        return retorno;
    }


}
