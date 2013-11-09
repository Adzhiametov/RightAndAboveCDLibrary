package com.rightandabove.cdlibrary.controller;

import com.rightandabove.cdlibrary.entity.Catalog;
import com.rightandabove.cdlibrary.entity.CompactDisc;
import com.rightandabove.cdlibrary.io.XMLReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Arsen Adzhiametov on 11/7/13 in IntelliJ IDEA.
 */
@Controller
public class PaginationController {

    private static final String HOLDER_KEY = "PaginationController_listHolder";
    private static final String NEXT = "next";
    private static final String PREVIOUS = "prev";
    private static final String FIRST = "first";
    private static final int ITEMS_ON_PAGE = 5;

    private static String relativeDestinationPath = "/WEB-INF/catalog/catalog.xml";

    @Autowired
    XMLReadWriteAccess<Catalog> xmlReadWriteAccess;
    @Autowired
    ServletContext servletContext;

    @RequestMapping(value = "/show/{page}", method = RequestMethod.GET)
    public String handleRequest(@PathVariable(value = "page") String page, HttpServletRequest request, Model model) throws Exception {
        PagedListHolder listHolder;
        if (page.equals(FIRST)) {
            Set<CompactDisc> discsSet = xmlReadWriteAccess.readFromFile(servletContext.getRealPath(relativeDestinationPath), Catalog.class).getCds();
            listHolder = new PagedListHolder(new ArrayList(discsSet));
            listHolder.setPageSize(ITEMS_ON_PAGE);
            request.getSession().setAttribute(HOLDER_KEY, listHolder);

        } else {
            listHolder = (PagedListHolder) request.getSession().getAttribute(HOLDER_KEY);
            if (listHolder == null) {
                model.addAttribute("message", "Something goes wrong. Please start over again.");
                return "show_file";
            }
            if (NEXT.equals(page)) {
                listHolder.nextPage();
            } else if (PREVIOUS.equals(page)) {
                listHolder.previousPage();
            }
        }

        model.addAttribute("listHolder", listHolder);
        return "show_file";
    }
}


