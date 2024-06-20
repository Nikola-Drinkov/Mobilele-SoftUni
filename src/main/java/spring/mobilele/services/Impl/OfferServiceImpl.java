package spring.mobilele.services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spring.mobilele.models.dtos.AddOfferDTO;
import spring.mobilele.models.dtos.OfferDetailsDTO;
import spring.mobilele.models.entities.Model;
import spring.mobilele.models.entities.Offer;
import spring.mobilele.repositories.ModelRepository;
import spring.mobilele.repositories.OfferRepository;
import spring.mobilele.services.OfferService;

import java.time.Instant;

@Service
public class OfferServiceImpl implements OfferService {
    private OfferRepository offerRepository;
    private ModelRepository modelRepository;
    private ModelMapper modelMapper;

    public OfferServiceImpl(OfferRepository offerRepository, ModelRepository modelRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long saveOffer(AddOfferDTO addOfferDTO) {
        Offer offer = this.modelMapper.map(addOfferDTO, Offer.class);
        offer.setCreated(Instant.now());
        offer.setModified(Instant.now());

        Model model = modelRepository.findFirstByName(addOfferDTO.getModel());
        offer.setModel(model);

        return offerRepository.save(offer).getId();
    }

    @Override
    public OfferDetailsDTO getOfferDetails(Long id) {
        Offer offer = this.offerRepository.findById(id).orElse(null);
        OfferDetailsDTO offerDetailsDTO = modelMapper.map(offer, OfferDetailsDTO.class);
        offerDetailsDTO.setModel(offer.getModel().getName());
        offerDetailsDTO.setBrand(offer.getModel().getBrand().getName());
        return offerDetailsDTO;
    }
}
