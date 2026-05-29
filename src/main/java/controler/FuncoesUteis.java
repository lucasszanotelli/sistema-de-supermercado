/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import model.Endereco;
import org.json.JSONObject;

/**
 *
 * @author lucas
 */
public class FuncoesUteis {

	public static Endereco consultaCep(String cep) throws IOException {
		String cepLimpo = cep == null ? "" : cep.replaceAll("\\D", "");
		if (cepLimpo.length() != 8) {
			return null;
		}

		URL url = new URL("https://viacep.com.br/ws/" + cepLimpo + "/json/");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);

		int status = connection.getResponseCode();
		InputStream stream = (status >= 200 && status < 300)
				? connection.getInputStream()
				: connection.getErrorStream();
		if (stream == null) {
			return null;
		}

		StringBuilder response = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
		}

		JSONObject jsonObject = new JSONObject(response.toString());
		if (jsonObject.optBoolean("erro", false)) {
			return null;
		}

		Endereco ender = new Endereco();
		ender.setCep(jsonObject.optString("cep", cepLimpo));
		ender.setBairro(jsonObject.optString("bairro", ""));
		ender.setCidade(jsonObject.optString("localidade", ""));
		ender.setUf(jsonObject.optString("uf", ""));
		ender.setRua(jsonObject.optString("logradouro", ""));
		return ender;
	}

}
