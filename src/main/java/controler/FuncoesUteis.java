///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package controler;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import org.json.JSONObject;
//
///**
// *
// * @author lucas
// */
//public class FuncoesUteis {
//
//    public static Endereco consultaCep(String cep) throws IOException {
//
//        Endereco ender = null;
//
//        URL url = new URL("https://viacep.com.br/ws/" + cep + "/json/");
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//
//        BufferedReader reader = new BufferedReader(
//                new InputStreamReader(connection.getInputStream()));
//
//        StringBuilder response = new StringBuilder();
//        String line;
//
//        while ((line = reader.readLine()) != null) {
//            response.append(line);
//        }
//
//        reader.close();
//
//        JSONObject jsonObject = new JSONObject(response.toString());
//
//        if (!jsonObject.has("erro")) {
//            ender = new Endereco();
//            ender.setBairro(jsonObject.getString("bairro"));
//            ender.setCidade(jsonObject.getString("localidade"));
//            ender.setUf(jsonObject.getString("uf"));
//        } else {
//            System.out.println("CEP não encontrado");
//        }
//
//        return ender;
//    }
//
//}
