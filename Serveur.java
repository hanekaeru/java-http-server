import java.net.*;
import java.util.Arrays;
import java.io.*;

/**
 * @author kgerard
 * @author acano
 */

public class Serveur{
    // Variables générales liées au serveur et aux sockets.
    ServerSocket srvk;
    DataOutputStream dos = null;
    BufferedReader br = null;
    Socket sck = null;

    // Éléments qui vont nous servir a établir une entête clair et complet.
    String descServer = "Server: Simple Serveur de TP ULR";
    String statusHeaderRequest = null;      // StatusCode de l'entête (par ex: 404 si erreur ou 200 si succès)
    String contentTypeRequest = null;       // Type de contenu (le plus souvent : text/html)
    String contentRequest = null;           // Contenu de la requête
    String contentLengthRequest = null;     // Longueur de la rêquete

    /**
     * Constructeur de notre classe Serveur
     * @param port int : Port sur lequel notre serveur va tourner. Il aura été choisi par l'utilisateur lors du lancement
     * du serveur.
     */
    public Serveur (int port) throws IOException {
        srvk = new ServerSocket (port);
    }

    /**
     * Méthode qui va permettre de lancer le serveur local. Il tournera en continu sauf en cas d'erreur I/O. Il y a fermetture
     * du socket après chaque requête.
     */
    public void go (){
        while (true){
            try {
                sck = srvk.accept();
                dos = new DataOutputStream(sck.getOutputStream());
                br = new BufferedReader(new InputStreamReader(sck.getInputStream()));
                traiterRequete(br);
                sck.close();
            } catch (IOException e) { 
                System.out.println("[!]I/O Error : "+ e);
            }
        }
    }

    /**
     * Cette méthode va traiter les requêtes associées au br envoyé en paramètre et va s'occuper de savoir si c'est
     * une requête GET ou bien une requête POST.
     * Elle permet également, à chaque requête débutée de savoir quels sont les éléments qui composent la requête reçue
     * comme le fichier à afficher, la méthode (GET ou POST), ... Même si ces informations étaient principalement nécessaires
     * au débug, nous avons décidé de les laisser car on les trouvait importantes.
     * @param br BufferedReader
     * @throws IOException
     */
    public void traiterRequete(BufferedReader br) throws IOException {
        int i = 0;
        String requestLine = br.readLine();
        if(requestLine != null) {
            // Si la requête est en GET alors on appelle notre méthode GET qui va gérer l'affichage de la page.
            if(requestLine.startsWith("GET")) {
                initGETrequest(requestLine.substring(5, requestLine.length()-9));
            }
            // Si la méthode est en POST alors on appelle notre méthode POST qui va gérer les données envoyées en POST.
            if(requestLine.startsWith("POST")) {
                initPOSTrequest(requestLine.substring(6, requestLine.length()-9));
            }
        } else { }
    }

    /**
     * Méthode qui va nous permettre d'afficher une page web car notre client a fait une requête en GET.
     * Cette méthode, en fonction du GET recu, va afficher une page, par exemple, si nous avons :
     * GET /exemple.html
     * Notre programme va directement aller chercher un fichier exemple.html présent à la racine du projet
     * si elle existe, elle sera affichée, sinon on aura un message d'erreur.
     * @param f String : Fichier qui doit être affiché sur notre page web.
     * @throws IOException
     */
    private void initGETrequest(String f) throws IOException {
        // On créer une variable file qui va concerner le fichier que nous avons envoyé en paramètre.
        File file = new File(f);
        // Si ce fichier existe alors ...
        if(file.exists()){
            // On établie notre status de retour à 200 (succès).
            statusHeaderRequest = "HTTP/1.1 200 OK";
            // Si le fichier se termine .html ou bien .htm (comme demandé dans le code source du TP3 sur Moodle)
            if (f.endsWith(".html")) {
                contentTypeRequest = "Content-Type: text/html";
            }
            // Longueur du contenu
            contentLengthRequest ="Content-Length: " + Long.toString(file.length());
            // On forme notre entête (header) avec les informations que nous avons récupéré auparavant.
            entete();
            // On récupère le contenu de notre fichier file de tout à l'heure ... 
            FileInputStream fis = new FileInputStream(file);
            // ... et on envoie le tout.
            envoiFichier(fis, dos);
            // On ferme le FileInputStream car nous n'avons plus besoin.
            fis.close();
        // Si le fichier n'existe pas.
        } else {
            // On envoie une simple entête précisant que le fichier n'a pas été trouvé et on en informe l'utilisateur.
            // Le code utilisé ci-dessous et utilisé de la même façon que ci-dessus.
            contentRequest = "File not Found" + "\r\n";
            statusHeaderRequest = "HTTP/1.1 404 Not Found";
            contentTypeRequest = "Content-Type: text/html";
            contentLengthRequest ="Content-Length: " + Long.toString(contentRequest.length());
            entete();
            envoi(contentRequest);
        }
    }

    private void initPOSTrequest(String f) throws IOException {
        System.out.println("POST");
    }

    private void envoiFichier(FileInputStream fis, OutputStream os) throws IOException
    {
        byte[] buffer = new byte[1024];
        int bytes = 0;
        while ((bytes = fis.read(buffer)) != -1 ) {
            os.write(buffer, 0, bytes);
        }
        envoi("\r\n");
    }

    

    private void envoi(String m) throws IOException {
        System.out.print("[Logs] " + m);
        dos.write(m.getBytes());
    }

    /**
     * Méthode qui va permettre de consituer notre
     * @throws IOException
     */
    private void entete() throws IOException {
        envoi(statusHeaderRequest+"\r\n");
        envoi(descServer+"\r\n");
        envoi(contentTypeRequest+"\r\n");
        envoi(contentLengthRequest+"\r\n");
        envoi("\r\n");
    }

    /**
     * Méthode main de notre classe. Celle qui sera lancée après lancé le script start.bat ou start.sh.
     * @param args[] String : Liste des arguments qui ne contient que le port de démarrage du serveur.
     */
    public static void main (String args []) throws IOException {
        System.out.println("[!]Server started.");
        Serveur s = new Serveur(Integer.parseInt(args[0]));
        s.go();
        System.out.println("[!]Server stopped.");
    }
}   