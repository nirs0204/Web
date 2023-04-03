package etu2061.framework.servlet;

import etu2061.framework.Mapping;
import etu2061.framework.annotation.UrlAnnotation;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> MappingUrls;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            String requestUrl = request.getRequestURL().toString();
            this.insertHashMap("etu2061.framework.bdd");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Ma Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Bienvenue sur ma Servlet!</h1>");
            out.println("<p>URL de la requete: " + requestUrl + "</p>");
            for (Map.Entry<String, Mapping> entry : this.MappingUrls.entrySet()) {
                String cle = entry.getKey();
                Mapping valeur = entry.getValue();
                out.println("<p>Annotation : " + cle + ", Classe : " + valeur.getClassName() + ", Methode : " + valeur.getMethod() + "</p>");
                System.out.println("Annotation : " + cle + ", Classe : " + valeur.getClassName() + ", Methode : " + valeur.getMethod());
            }
            out.println("</html>");
            out.println("</body>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

//    packageName : etu2061.framework.bdd
    public ArrayList<Class<?>> getClasses(String packageName) throws ClassNotFoundException {
        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        String path = packageName.replace('.', File.separatorChar);
        String[] classPathEntries = System.getProperty("java.class.path").split(
                System.getProperty("path.separator"));
        String name;
        for (String classpathEntry : classPathEntries) {
            File base = new File(classpathEntry + File.separatorChar + path);
            if (!base.exists()) {
                continue;
            }
            File[] classFiles = base.listFiles((File pathname) -> {
                return pathname.isFile() && pathname.getName().endsWith(".class");
            });
            for (File classFile : classFiles) {
                name = classFile.getName();
                name = name.substring(0, name.length() - ".class".length());
                classes.add(Class.forName(packageName + '.' + name));
            }
        }
        return classes;
    }

    public ArrayList<Mapping> trouverMethodesAnnotees(Class<?> classe, Class<? extends Annotation> annotation) {
        ArrayList<Mapping> meth = new ArrayList<Mapping>();
        for (Method methode : classe.getDeclaredMethods()) {
            if (methode.isAnnotationPresent(annotation)) {
                Mapping map = new Mapping(classe.getSimpleName(), methode.getName());
                meth.add(map);
            }
        }
        return meth;
    }

    public String readAnnotation(Mapping map) throws Exception {
        Class<?> classe = Class.forName(map.getClassName());
        Method methode = classe.getMethod(map.getMethod());
        UrlAnnotation annotation = methode.getAnnotation(UrlAnnotation.class);
        String valeur = annotation.url();
        return valeur;
    }

    public void insertHashMap(String packageName) throws Exception{
        ArrayList<Class<?>> listClass = this.getClasses(packageName);
        ArrayList<Mapping> listMapping = new ArrayList<Mapping>();
        for (int i = 0; i < listClass.size(); i++) {
            ArrayList<Mapping> list = this.trouverMethodesAnnotees(listClass.get(i).getClass(), UrlAnnotation.class);
            for (int j = 0; j < list.size(); j++) {
                listMapping.add(list.get(j));
            }
        }
        HashMap<String, Mapping> hm = new HashMap<String, Mapping>();
        for (int i = 0; i < listMapping.size(); i++) {
            String annot = this.readAnnotation(listMapping.get(i));
            hm.put(annot, listMapping.get(i));
        }
        this.MappingUrls = hm;
    }
}
