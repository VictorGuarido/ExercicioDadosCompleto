package br.com.mastertech.dnd;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.Normalizer;

@Controller
public class AppController {

    @GetMapping
    public String mostrarIndex(){
        return "index";
    }

    @GetMapping("/form")
    public String mostrarForm(){
        return "form";
    }

    @PostMapping("/resultado")
    public String mostrarResposta(@ModelAttribute Formulario formulario, Model model)throws DadoInvalidoException{
        try {
            Dado dado = new Dado(formulario.getLados());
            Sorteador sorteador = new Sorteador(dado);
            Resultado resultado = sorteador.sortear(formulario.getVezes());

            model.addAttribute("resultado", resultado);

//        for (int numero : resultado.getNumeros()){
//            System.out.println(numero);
//        }

            return "resultado";
        }catch (DadoInvalidoException e){
            return "erro";
        }

    }

}
