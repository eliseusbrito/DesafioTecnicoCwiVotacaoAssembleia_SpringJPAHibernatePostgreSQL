package VotacaoAssembleia.apiCpf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class APIRest {

    public Integer situacaoCPF(String cpfAssociado) {
        try {
            String url = "https://user-info.herokuapp.com/users/"+cpfAssociado;

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + url);
                    return 200;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output = "";
            String line;
            while ((line = br.readLine()) != null) {
                output += line;
            }

            conn.disconnect();

            System.out.println("output: "+ output);
            String resposta = "{\"status\":\"ABLE_TO_VOTE\"}";
            System.out.println("resposta: "+resposta);

            int resp = output.compareToIgnoreCase(resposta);
            System.out.println("resp: "+resp);
            if(resp==0){
                System.out.println("OKKKKKKKKKKKKK");
                return resp;}

        } catch (IOException ex) {
            Logger.getLogger(APIRest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 20;
    }
}
