package web;

import domain.entities.Type;
import domain.models.service.ProductServiceModel;
import service.ProductService;
import util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products/create")
public class ProductCreateServlet extends HttpServlet {

    private final static String HTML_CREATE_PRODUCT_FILE_PATH = "C:\\Users\\joroi\\Desktop\\SoftUni\\JavaWeb\\servletAPI\\src\\main\\resources\\views\\product-create.html";

    private final HtmlReader htmlReader;
    private final ProductService productService;

    @Inject
    public ProductCreateServlet(HtmlReader htmlReader, ProductService productService) {
        this.htmlReader = htmlReader;
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String html = htmlReader.readFile(HTML_CREATE_PRODUCT_FILE_PATH)
                .replace("{{options}}",getTypeOptions());

        resp.getWriter().println(html);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ProductServiceModel productServiceModel = new ProductServiceModel();
        productServiceModel.setName(req.getParameter("name"));
        productServiceModel.setDescription(req.getParameter("description"));
        productServiceModel.setType(req.getParameter("type"));

        this.productService.saveProduct(productServiceModel);

        resp.sendRedirect("/home");
    }

    private String getTypeOptions(){
        StringBuilder optionList = new StringBuilder();

        for (Type type : Type.values()) {
            optionList.append(String.format("<option name=\"%s\">%s</option>",type ,type));
        }

        return optionList.toString();
    }
}
