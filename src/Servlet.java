

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class Servlet extends HttpServlet {
    final static String css = "<style>body{ font-family: sans-serif;}</style>";
    final static String head = "<html><head>"+css+"</head><body>";
    final static String menu =  "<div><a href='/Esercizio_form/reg'>Register</a> <a href='/Esercizio_form/login'>Login</a> <a href='/Esercizio_form/logout'>Logout</a> <a href='/Esercizio_form/profile'>Profile</a></div>";
    final static String foot = "</body></html>";

    /**
     * @see HttpServlet#HttpServlet()
     */

    /*
    *
    *
    * QUANDO CLICCO LOGIN SE C'è GIA UNA SESSIONE USER ATTIVA, NON DEVE VISUALIZZARE IL MESSAGGIO+REGISTRAZIONE OBBLIGATA
    * */


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //response.getWriter().append("Served at: ").append(request.getContextPath());
        response.setContentType("text/html");
        HttpSession session;
        PrintWriter out = new PrintWriter(response.getWriter());
        //User user = (User) session.getAttribute("user");
        User user;


        switch (request.getRequestURI())//ottengo la parte dell'url dopo la prima slash
        {//START SWITCH
            case "/Esercizio_form/":
                session = request.getSession();
                user = (User) session.getAttribute("user");
                out.println(head + menu + foot); //stampa semplicemente la pagina "iniziale"
                break;

            case "/Esercizio_form/login":
                if (request.getParameterMap().containsKey("last") && request.getParameter("last").equals("profile")) {
                    out.println("<h2>Operazione non possibile!</h2><p>Per visualizzare il profilo devi prima loggarti!</p>");
                } else {
                    out.println("");
                }
                session = request.getSession();
                if (session == null) {
                    response.sendRedirect(request.getContextPath() + "/Esercizio_form/reg?last=login");
                } else {//!!!!!!!!!!!
                    user = (User) session.getAttribute("user");
                    if (user == null) {
                        response.sendRedirect(request.getContextPath() + "/Esercizio_form/reg?last=login");
                    } else {
                        if (user.getLog()) {

                            out.println(head + menu + "<h2>Sei già loggato!</h2>" + foot);
                        } else {
                            if (user == null) {
                                response.sendRedirect(request.getContextPath() + "/Esercizio_form/reg?last=login");
                            } else {
                                out.println(head + menu);
                                out.println("<form action='/Esercizio_form/login' method='post'><label>Username: </label><input type='text' name='username'><br><label>Password: </label><input type='password' name='password'><br><button type='submit'>submit</button></form>" + foot);
                                out.println(foot);

                            }
                        }
                    }
                }
                out.close();
                break;


                /*//session = request.getSession();
                //user = (User) session.getAttribute("user");
                //l'utente vuole visualizzare il profilo senza essersi loggato
                if (request.getParameterMap().containsKey("last") && request.getParameter("last").equals("profile"))
                {
                    out.println("Per visualizzare il profilo devi prima loggarti!");

                    //costruzione form
                    out.println(head + menu);
                    out.println("<form action='/Esercizio_form/login' method='post'><label>Username: </label><input type='text' name='username'><br><label>Password: </label><input type='password' name='password'><br><button type='submit'>submit</button></form>" + foot);

                }else if (user == null) {//l'utente vuole loggarsi ma non si � registrato (nessuna sessione istanziata)
                    response.sendRedirect(request.getContextPath() + "/Esercizio_form/reg?last=login");
                }else {
                    if(!user.getLog())//se l'utente non � gia loggato
                    {
                        //nessun problema, costruzione form per il login
                        out.println(head + menu);
                        out.println("<form action='/Esercizio_form/login' method='post'><label>Username: </label><input type='text' name='username'><br><label>Password: </label><input type='password' name='password'><br><button type='submit'>submit</button></form>" + foot);
                        out.println(foot);

                    }
                    else
                    {
                        out.println(head + menu + "Sei già loggato!" + foot);
                    }
                }*/


            case "/Esercizio_form/reg":
                session = request.getSession();
                user = (User) session.getAttribute("user");
                //l'utente vuole visualizzare il profilo o ha provato a loggarsi ma non è registrato
                //if (session == null) {
                if(user==null)
                {
                    out.println(head + menu + "<br><form action='/Esercizio_form/reg' method='post'><label>Nome: </label><input type='text' name='nome'><br><label>Cognome: </label><input type='text' name='cogn'><br><label>Username: </label><input type='text' name='username'><br><label>Password: </label><input type='password' name='password'><br><button type='submit'>submit</button></form>" + foot);


                    if ((request.getParameterMap().containsKey("last") && request.getParameter("last").equals("profile")) || (request.getParameterMap().containsKey("last") && request.getParameter("last").equals("login")))
                    {
                        out.println("<h2>Prima è necessario registrarsi!</h2><p>Inserisci i dati nel form.</p>");
                        //out.println("<form action='/Esercizio_form/reg' method='post'><label>Nome: </label><input type='text' name='nome'><br><label>Cognome: </label><input type='text' name='cogn'><br><label>Username: </label><input type='text' name='username'><br><label>Password: </label><input type='password' name='password'><br><button type='submit'>submit</button></form>" + foot);
                    }
                }
                //} else {
                if(session!=null) {
                    //user = (User) session.getAttribute("user");
                    if (user != null) {
                        /*out.println(head + menu + "<h2>AAAPrima è necessario registrarsi!</h2><p>Usa il form sottostante: </p>");
                        out.println("<form action='/Esercizio_form/reg' method='post'><label>Nome: </label><input type='text' name='nome'><br><label>Cognome: </label><input type='text' name='cogn'><br><label>Username: </label><input type='text' name='username'><br><label>Password: </label><input type='password' name='password'><br><button type='submit'>submit</button></form>" + foot);

                    } else {*/
                        //user != null ovvero l'utente ha gia istanziato una sessione dunque ha gia effettuato una registrazione
                        out.println(head + menu + "<h2>Sei loggato!</h2><p>Per effettuare una nuova registrazione devi fare il logout.</p>" + foot);
                    }
                }/*else
                {
                    out.println(head + menu + "<h2>CCCPrima è necessario registrarsi!</h2><p>Usa il form sottostante: </p>");
                    out.println("<form action='/Esercizio_form/reg' method='post'><label>Nome: </label><input type='text' name='nome'><br><label>Cognome: </label><input type='text' name='cogn'><br><label>Username: </label><input type='text' name='username'><br><label>Password: </label><input type='password' name='password'><br><button type='submit'>submit</button></form>" + foot);

                }*/

                //}

                out.close();
                break;

            case "/Esercizio_form/profile":

                session = request.getSession();
                if (session == null) {
                    response.sendRedirect(request.getContextPath() + "/Esercizio_form/reg?last=profile");
                } else {
                    user = (User) session.getAttribute("user");
                    if (user == null) {
                        response.sendRedirect(request.getContextPath() + "/Esercizio_form/reg?last=profile");
                    } else if (user.getLog()) {

                        out.println(head + menu);
                        out.println("<h2>Il tuo profilo</h2>");
                        out.println("<ul>");
                        out.println("<li>NOME: " + user.getName() + "</li>");
                        out.println("<li>COGNOME: " + user.getCogn() + "</li>");
                        out.println("<li>USERNAME: " + user.getUsername() + "</li>");
                        out.println("<li>PASSWORD: " + user.getPwd() + "</li>");
                        out.println("</ul>");
                        out.println(foot);

                    } else
                        response.sendRedirect(request.getContextPath() + "Esercizio_form/login/?last=profile");


                }
                out.close();
                break;


























               /* session = request.getSession();
                //user = (User) session.getAttribute("user");

                if(session == null)
                {
                    response.sendRedirect(request.getContextPath() + "/login?last=profile");
                }else
                {
                    user = (User) session.getAttribute("user");
                    out.println(head+menu);
                    out.println("<h2>Il tuo profilo</h2>");
                    out.println("<ul>");
                    out.println("<li>NOME: "+user.getName()+"</li>");
                    out.println("<li>COGNOME: "+user.getCogn()+"</li>");
                    out.println("<li>USERNAME: "+user.getUsername()+"</li>");
                    out.println("<li>PASSWORD: "+user.getPwd()+"</li>");
                    out.println("</ul>");
                    out.println(foot);
                }
                break;*/

            case "/Esercizio_form/logout":
                session = request.getSession();
                //user = (User) session.getAttribute("user");

                if (session != null)//true=loggato false=sloggato
                {
                    Object o = session.getAttribute("user");
                    if(o!=null)
                    {
                        user = (User) o;
                        user.setLog(false);
                        //session.invalidate();
                        out.println(head + menu + "<p>Log out avvenuto con successo.</p>" + foot);
                    }else
                    {
                        out.println(head + menu + "<h2>Non puoi effettuare il logout perchè non sei loggato!</h2>" + foot);

                    }

                } else {
                    out.println(head + menu + "<h2>Non puoi effettuare il logout perchè non sei loggato!</h2>" + foot);
                }
                out.close();
                break;

            default:
                out.println("Error 404: File not found!");
                break;


        }//END GET SWITCH
    }//END doGET

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //doGet(request, response);
        response.setContentType("text/html");
        HttpSession session;
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nome = request.getParameter("nome");
        String cogn = request.getParameter("cogn");


        //questo metodo viene invocato quando l'utente invia qualcosa col post (quindi tramite form)
        //a differenza di php, non controlliamo subito COSA ha inviato ma DOVE si trova

        switch (request.getRequestURI())//ottengo l'uri della request
        {//il caso "/" non si valuta perch� quella schermata consiste solo nella pagina con gli href
            case "/Esercizio_form/login":
                session = request.getSession();//inizializzo la sessione
                if(session==null)//nessuna registrazione
                {
                    response.sendRedirect(request.getContextPath() + "/reg?last=login");
                }else
                {
                    User user = (User) session.getAttribute("user");

                    if(user.getUsername().equals(username)&&user.getPwd().equals(password))
                    {
                        out.println(head + menu + "Bentornato " + user.getName() + "!" + foot);
                        user.setLog(true);
                    }
                    else
                    {
                        out.println(head + menu + "Dati non corretti!" + foot);
                    }
                }
                out.close();
                break;

            case "/Esercizio_form/reg":
                session = request.getSession(true);//if there is no current session and "create" is true, returns a new session.

                User user = new User(username,password,nome,cogn);
                session.setAttribute("user", user);//attribuisce alla key della sessione il valore user e gli "attacca" l'oggetto di tipo User
                user.setLog(true);
                out.println(head + menu + "<h3>Utente registrato correttamente!</h3>" + foot);

                //response.sendRedirect(request.getContextPath() + "/login");
                out.close();
                break;

            //non c'� invio di dati con un un form quando l'utente � in profile, quindi il caso non va sviluppato



            default: out.println("Error 404: File not found!");
                        out.close(); break;







        }

    }}
