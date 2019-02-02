package web;

import domain.models.service.ProductServiceModel;
import domain.models.view.ProductDetailsViewModel;
import service.ProductService;
import util.HtmlReader;
import util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products/details")
public class ProductDetailsServlet extends HttpServlet {

    private final static String HTML_PRODUCT_DETAIL_FILE_PATH = "C:\\Users\\joroi\\Desktop\\SoftUni\\JavaWeb\\servletAPI\\src\\main\\resources\\views\\product-details.html";

    private final HtmlReader htmlReader;
    private final ProductService productService;
    private final ModelMapper  modelMapper;

    @Inject
    public ProductDetailsServlet(HtmlReader htmlReader, ProductService productService, ModelMapper modelMapper) {
        this.htmlReader = htmlReader;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(
                parsedHtml(req.getQueryString().split("=")[1]));
    }

    private String parsedHtml(String entityName) throws IOException {

        ProductDetailsViewModel productDetailsViewModel = this.modelMapper.map(
                this.productService.findByName(entityName), ProductDetailsViewModel.class);

        String html = this.htmlReader.readFile(HTML_PRODUCT_DETAIL_FILE_PATH)
                .replace("{{productName}}", productDetailsViewModel.getName())
                .replace("{{productDescription}}", productDetailsViewModel.getDescription())
                .replace("{{productType}}", productDetailsViewModel.getType());

        return html;
    }
}
