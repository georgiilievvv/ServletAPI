package web;

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

@WebServlet("/")
public class indexServlet extends HttpServlet {

    private final static String HTML_INDEX_FILE_PATH = "";

    private final ModelMapper modelMapper;
    private final HtmlReader htmlReader;
    private final ProductService productService;

    @Inject
    public indexServlet(ModelMapper modelMapper, HtmlReader htmlReader, ProductService productService) {
        this.modelMapper = modelMapper;
        this.htmlReader = htmlReader;
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        resp.getWriter().println(this.htmlReader.readFile(HTML_INDEX_FILE_PATH));
    }
}
