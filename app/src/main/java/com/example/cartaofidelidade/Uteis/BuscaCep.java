package com.example.cartaofidelidade.Uteis;

import android.os.Build;
import android.os.StrictMode;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BuscaCep {

    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;

    public BuscaCep() {
        super();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<String> buscarCep(String cep)
    {
        String json;

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);

            URL url = new URL("http://viacep.com.br/ws/"+ cep +"/json");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream is = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            StringBuilder jsonSb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null){
                jsonSb.append(line);
            }

            json = jsonSb.toString();

            json = json.replaceAll("[{},:]", "");
            json = json.replaceAll("\"", "\n");
            String array[] = new String[30];
            array = json.split("\n");

            logradouro = array[7];
            bairro = array[15];
            cidade = array[19];
            uf = array[23];

            List<String> lista = new ArrayList<>();
            lista.add(logradouro);
            lista.add(bairro);
            lista.add(cidade);
            lista.add(uf);
            Log.e("msg","CEP encontrado!");
            return lista;

        } catch (Exception e) {
            Log.e("msg","CEP nao encontrado!");
            return null;
        }
    }

//    public String buscarCep(String cep) {
//        StringBuilder conteudoHTML = new StringBuilder();
//        URL tUrl;
//        InputStream tInput;
//        String tLinha;
//        try {
//            tUrl = new URL(String.format("http://viacep.com.br/ws/"+ cep +"/json"));
//            tInput = tUrl.openStream();
//
//            try (InputStreamReader tArq1 = new InputStreamReader(tInput);
//                 BufferedReader tArq2 = new BufferedReader(tArq1);) {
//                while (true) {
//                    tLinha = tArq2.readLine();
//                    if (tLinha == null) {
//                        break;
//                    }
//                    conteudoHTML.append(tLinha);
//                    conteudoHTML.append("\n");
//                }
//            } catch (MalformedURLException e1) {
//                System.out.println("URL inválida: " + e1.getMessage());
//            } catch (IOException e2) {
//                System.out.println("Erro na obtenção do objeto: " + e2.getMessage());
//            }
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            System.out.println(
//                    "Servidor Keyserver não localiza. Verificar se o acesso ao servidor é válido e liberado pela Internet.");
//        }
//        return conteudoHTML.toString();
//    }
}
