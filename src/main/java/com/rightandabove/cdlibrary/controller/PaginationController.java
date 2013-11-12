package com.rightandabove.cdlibrary.controller;

import com.rightandabove.cdlibrary.entity.Catalog;
import com.rightandabove.cdlibrary.entity.CompactDisc;
import com.rightandabove.cdlibrary.service.XMLReadWriteAccess;
import com.rightandabove.cdlibrary.service.HelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Arsen Adzhiametov on 11/7/13 in IntelliJ IDEA.
 *
 * PaginationController uses PagedListHolder to provide fake pagination.
 * If this is the first call, the file is read, parsed and list of objects placed in the session
 * via PageListHolder. If this is not first call, list of cd's pulled out of the
 * session and nextPage or prevPage methods called.
 * When working with large files, we can to do lazy loading of the file.
 * There are few approaches to do this trick.
 */
@Controller
public class PaginationController {

    private static final String HOLDER_KEY = "PaginationController_listHolder";
    private static final String NEXT = "next";
    private static final String PREVIOUS = "prev";
    private static final String FIRST = "first";
    private static final int ITEMS_ON_PAGE = 5;

    @Autowired
    XMLReadWriteAccess<Catalog> xmlReadWriteAccess;
    @Autowired
    HelperService helperService;

    @RequestMapping(value = "/show/{page}", method = RequestMethod.GET)
    public String handleRequest(@PathVariable(value = "page") String page, HttpServletRequest request, Model model) throws Exception {
        PagedListHolder listHolder;
        if (page.equals(FIRST)) {
            Catalog catalog = xmlReadWriteAccess.readFromFile(helperService.getXmlFilePath(), Catalog.class);
            Set<CompactDisc> discsSet = catalog.getCds();
            listHolder = new PagedListHolder(new ArrayList(discsSet));
            listHolder.setPageSize(ITEMS_ON_PAGE);
            request.getSession().setAttribute(HOLDER_KEY, listHolder);
        } else {
            listHolder = (PagedListHolder) request.getSession().getAttribute(HOLDER_KEY);
            if (listHolder == null) {
                model.addAttribute("message", "something.goes.wrong.please.start.over.again");
                return "show";
            }
            if (NEXT.equals(page)) {
                listHolder.nextPage();
            } else if (PREVIOUS.equals(page)) {
                listHolder.previousPage();
            }
        }
        model.addAttribute("listHolder", listHolder);
        return "show";
    }
}


