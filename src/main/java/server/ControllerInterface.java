package server;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Path("ScoopSoftware")
public class ControllerInterface {

    @GET
    @Path("welcome")
    public Response enterMainMenu() throws IOException {
        String pageTemplatePath = "/static/page_template.html";
        String page = new String(this.getClass().getResourceAsStream(pageTemplatePath).readAllBytes());
        page = page.replace("${title}", "Scoop Software GmbH");
        Document doc = Jsoup.parse(page);
        /*String menuTemplatePath = "/menu_template.html";
        String menu = new String(this.getClass().getResourceAsStream("/static/page_template.html").readAllBytes());
        menu = menu.replace("${menuTitle}", "Sustainability at Scoop");
        doc.appendChild(Jsoup.parse(menu).body());*/
        return Response
                .ok(doc.html(), MediaType.TEXT_HTML)
                .encoding(StandardCharsets.UTF_8.name())
                .build();
    }
}
