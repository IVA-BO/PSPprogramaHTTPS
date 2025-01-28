import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Clase principal que realiza una solicitud HTTPS a un servidor.
 */
public class HTTPSClient {

    /**
     * Método principal que ejecuta la solicitud HTTPS.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan en este programa).
     */
    public static void main(String[] args) {
        try {
            // URL del servidor HTTPS al que se realizará la solicitud
            URL url = new URL("https://jsonplaceholder.typicode.com/posts/1");

            // Abrir una conexión HTTPS
            HttpsURLConnection conexion = (HttpsURLConnection) url.openConnection();

            // Configurar el método de solicitud (GET en este caso)
            conexion.setRequestMethod("GET");

            // Obtener el código de respuesta del servidor
            int codigorepuesta = conexion.getResponseCode();
            System.out.println("Código de respuesta: " + codigorepuesta);

            // Leer la respuesta del servidor
            if (codigorepuesta == HttpsURLConnection.HTTP_OK) { // Si la respuesta es exitosa
                BufferedReader in = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                // Leer la respuesta línea por línea
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Mostrar la respuesta en la consola
                System.out.println("Respuesta del servidor: " + response.toString());
            } else {
                System.out.println("Error en la solicitud. Código de respuesta: " + codigorepuesta);
            }
        } catch (Exception e) {
            // Manejar cualquier excepción que ocurra durante la conexión
            e.printStackTrace();
        }
    }
}