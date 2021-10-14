/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.modeloExamen.controller;

import com.example.modeloExamen.entity.Producto;
import com.example.modeloExamen.repository.PostProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerProducto {

    @Autowired
    private PostProducto postProducto;

    @RequestMapping("/main")
    public String mensaje(Model model) {
        model.addAttribute("mensaje", "Bienvenidos Thymeleaf");
        return "main";
    }

    @RequestMapping("/")
    public String mensa(Model model) {
        model.addAttribute("mensa", "Bienvenidos Thymeleaf");
        return "index";
    }

    @RequestMapping("/productos")
    public String post(Model model) {
        model.addAttribute("productos", postProducto.findAll());
        return "producto";
    }

    @RequestMapping("/formProdu")
    public String create(Model model) {
        return "addProducto";
    }

    @RequestMapping("/producadd")
    public String guardar(@RequestParam String producto, @RequestParam String precio, @RequestParam String stock, Model model) {
        Producto prod = new Producto();
        prod.setProducto(producto);
        prod.setPrecio(precio);
        prod.setStock(stock);
        System.out.println("sout:" + prod.getProducto() + "/" + prod.getPrecio() + "/" + prod.getStock() + "/");
        postProducto.save(prod);
        return "redirect:/productos";
    }

    @RequestMapping("/producdel/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        System.out.println("ID: " + id);
        Producto producto = postProducto.findById(id).orElse(null);
        postProducto.delete(producto);
        return "redirect:/productos";
    }

    @RequestMapping("/producedit/{id}")
    public String edit(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("producto", postProducto.findById(id).orElse(null));
        return "editProducto";
    }

    @RequestMapping("/producupdate")
    public String update(@RequestParam Long id, @RequestParam String producto, @RequestParam String precio, @RequestParam String stock) {
        Producto prod = postProducto.findById(id).orElse(null);
        prod.setProducto(producto);
        prod.setPrecio(precio);
        prod.setStock(stock);
        postProducto.save(prod);
        return "redirect:/productos";
    }
}
  
