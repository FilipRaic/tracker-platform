package hr.tvz.trackerplatform.wellbeing_tip.service;

import hr.tvz.trackerplatform.daily_check.model.DailyQuestion;
import hr.tvz.trackerplatform.shared.mapper.Mapper;
import hr.tvz.trackerplatform.wellbeing_tip.dto.WellbeingTipDTO;
import hr.tvz.trackerplatform.wellbeing_tip.repository.WellbeingTipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WellbeingTipServiceImpl implements WellbeingTipService {

    private final Mapper mapper;
    private final WellbeingTipRepository wellbeingTipRepository;

    @Override
    public List<WellbeingTipDTO> findByCategoryAndAndScore(List<DailyQuestion> listQuestions) {
        return listQuestions.stream()
                .map(dailyQuestion -> wellbeingTipRepository.findByCategoryAndAndScore(dailyQuestion.getCategory(), dailyQuestion.getScore()))
                .filter(Optional::isPresent)
                .map(tipOptional -> mapper.map(tipOptional.get(), WellbeingTipDTO.class))
                .toList();
    }
}
