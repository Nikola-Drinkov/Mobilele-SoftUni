package spring.mobilele.controllers;

import jakarta.validation.Valid;
import org.apache.catalina.Engine;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spring.mobilele.enums.EngineTypesEnum;
import spring.mobilele.enums.TransmissionsEnum;
import spring.mobilele.models.dtos.AddOfferDTO;
import spring.mobilele.models.dtos.OfferDetailsDTO;
import spring.mobilele.models.entities.Brand;
import spring.mobilele.models.entities.Model;
import spring.mobilele.models.entities.Offer;
import spring.mobilele.services.Impl.BrandServiceImpl;
import spring.mobilele.services.Impl.ModelServiceImpl;
import spring.mobilele.services.Impl.OfferServiceImpl;
import spring.mobilele.services.OfferService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private BrandServiceImpl brandService;
    private ModelServiceImpl modelService;
    private OfferServiceImpl offerService;

    public OfferController(BrandServiceImpl brandService, ModelServiceImpl modelService, OfferServiceImpl offerService) {
        this.brandService = brandService;
        this.modelService = modelService;
        this.offerService = offerService;
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

    @GetMapping("/{id}")
    public String offerDetails(@PathVariable("id") Long id,
                               org.springframework.ui.Model model) {
        OfferDetailsDTO offerDetailsDTO = offerService.getOfferDetails(id);
        model.addAttribute("offerDetails", offerDetailsDTO);

        return "details";
    }

    @PostMapping("/add")
    public String addOffer(@ModelAttribute("addOfferDTO") @Valid AddOfferDTO addOfferDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addOfferDTO", addOfferDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDTO", bindingResult);
            return "redirect:/offers/add";
        }

        Long offerId = offerService.saveOffer(addOfferDTO);
        return "redirect:/offers/" + offerId;
    }
}
