package edu.upc.eseiaat.pma.webconnect;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Marta on 18/10/17.
 */

class WebFetcher {

    static String getUrl(String s_url) {   // funció que retorna un string
            // 1.Creem un objecte de tipus URL
            try {
                URL url = new URL(s_url);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // el que es conecta
                if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) { // si la conexió és difarent 200 ( està bé) marxem
                    return "<error: No HTTP_OK>";
                }
                // ara venen els bytes per la xarxa
                InputStream in = conn.getInputStream(); // passa'm els bytes i els vaig enxtraient i els guardo a un array
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                coppyStream(in, out); // funció inventada (alt+Enter --> create method)
                return out.toString(); // retorno la sortida amb els bytes ja copiats
            } catch (IOException e) {
                e.printStackTrace();
                return "<error IOException>";
            }
    }


    private static void coppyStream(InputStream in, ByteArrayOutputStream out)
        //Gestionar els error en vermell: Ens diu que no hem gestionat una excepció, amb això aquesta funció gestiona exepcions
            throws IOException
    {
        // Copiar els bits d'un Steam a un altre
        byte[] bytes = new byte[1024];
        int nbytes = in.read(bytes); // llegim els bytes de l'entrada
        while (nbytes > 0) {
            out.write(bytes, 0, nbytes);
            nbytes = in.read(bytes);
        }
    }
}


