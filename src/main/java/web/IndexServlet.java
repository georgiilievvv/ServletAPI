package web;

import domain.entities.Product;
import domain.models.service.ProductServiceModel;
import domain.models.view.AllProductsViewModel;
import repository.ProductRepository;
import service.ProductService;
import util.HtmlReader;
import util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/home")
public class IndexServlet extends HttpServlet {

    private final static String HTML_INDEX_FILE_PATH = "C:\\Users\\joroi\\Desktop\\SoftUni\\JavaWeb\\servletAPI\\src\\main\\resources\\views\\index.html";

    private final ModelMapper modelMapper;
    private final HtmlReader htmlReader;
    private final ProductService productService;
    private final ProductRepository productRepository;

    @Inject
    public IndexServlet(ModelMapper modelMapper, HtmlReader htmlReader, ProductService productService, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.htmlReader = htmlReader;
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String html = this.htmlReader.readFile(HTML_INDEX_FILE_PATH)
                .replace("{{productList}}", productList());

        resp.getWriter().println(html);
    }

    private String productList() {
        List<AllProductsViewModel> products = this.productService.allProducts()
                .stream()
                .map(psm -> this.modelMapper.map(psm, AllProductsViewModel.class))
                .collect(Collectors.toList());

        StringBuilder result = new StringBuilder();

        for (AllProductsViewModel product : products) {
            result
                    .append(String.format("<li><a href=\"/products/details?name=%s\">%s</a></li>"
                            ,product.getName(), product.getName()));
        }

        return result.toString();
    }
}
