/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.modeloExamen.controller;

import com.example.modeloExamen.entity.Categoria;
import com.example.modeloExamen.repository.PostCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerCategoria {

    @Autowired
    private PostCategoria postCategoria;

    @RequestMapping("/categorias")
    public String post(Model model) {
        model.addAttribute("categorias", postCategoria.findAll());
        return "categoria";

    }

    @RequestMapping("/formCat")
    public String create(Model model) {
        return "addCategoria";
    }

    @RequestMapping("/categadd")
    public String guardar(@RequestParam String nombre, Model model) {
        Categoria cate = new Categoria();
        cate.setNombre(nombre);
        System.out.println("sout:" + cate.getNombre()+"/");
        postCategoria.save(cate);
        return "redirect:/categorias";
    }

    @RequestMapping("/categdel/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        System.out.println("ID: " + id);
        Categoria categoria = postCategoria.findById(id).orElse(null);
        postCategoria.delete(categoria);
        return "redirect:/categorias";
    }

    @RequestMapping("/categedit/{id}")
    public String edit(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("categoria", postCategoria.findById(id).orElse(null));
        return "editCategoria";
    }

    @RequestMapping("/categupdate")
    public String update(@RequestParam Long id, @RequestParam String nombre) {
        Categoria cat = postCategoria.findById(id).orElse(null);
        cat.setNombre(nombre);
        postCategoria.save(cat);
        return "redirect:/categorias";
    }

}
