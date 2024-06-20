package spring.mobilele.controllers;

import jakarta.validation.Valid;
import org.apache.catalina.Engine;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.mobilele.enums.EngineTypesEnum;
import spring.mobilele.enums.TransmissionsEnum;
import spring.mobilele.models.dtos.AddOfferDTO;
import spring.mobilele.models.entities.Brand;
import spring.mobilele.models.entities.Model;
import spring.mobilele.models.entities.Offer;
import spring.mobilele.services.Impl.BrandServiceImpl;
import spring.mobilele.services.Impl.ModelServiceImpl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private BrandServiceImpl brandService;
    private ModelServiceImpl modelService;

    public OfferController(BrandServiceImpl brandService, ModelServiceImpl modelService) {
        this.brandService = brandService;
        this.modelService = modelService;
    }

    @ModelAttribute(name = "brands")
    public List<Brand> populateBrands() {
        return brandService.getAllBrands()
                .stream()
                .sorted(Comparator.comparing(Brand::getName))
                .collect(Collectors.toUnmodifiableList());
    }

    @ModelAttribute(name = "models")
    public List<Model> populateModels() {
        return modelService.getAllModels()
                .stream()
                .sorted(Comparator.comparing(Model::getName))
                .collect(Collectors.toUnmodifiableList());
    }

    @ModelAttribute(name = "transmissionTypes")
    public TransmissionsEnum[] populateTransmissionTypes() {
        return TransmissionsEnum.values();
    }

    @ModelAttribute(name = "engineTypes")
    public EngineTypesEnum[] populateEngineTypes() {
        return EngineTypesEnum.values();
    }

    @GetMapping("/add")
    public String offers(org.springframework.ui.Model model) {
        if(!model.containsAttribute("addOfferDTO")) {
            model.addAttribute("addOfferDTO", new AddOfferDTO());
        }
        return "offer-add";
    }

    @PostMapping("/add")
    public String addOffer(@ModelAttribute("addOfferDTO") @Valid AddOfferDTO addOfferDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addOfferDTO", addOfferDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO", bindingResult);
            return "redirect:/offers/add";
        }
        System.out.println(addOfferDTO);
        return "redirect:/";
    }
}
