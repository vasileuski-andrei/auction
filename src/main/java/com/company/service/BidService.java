package com.company.service;

import com.company.dao.BidDao;
import com.company.dto.BidDto;
import com.company.dto.PlaceBidDto;
import com.company.exception.LotSaleTimeElapsedException;
import com.company.exception.ValidationException;
import com.company.mapper.CreateBidMapper;
import com.company.util.LotCountdown;
import com.company.validator.Error;
import com.company.validator.PlaceBidValidator;
import com.company.validator.ValidationResult;
import lombok.NoArgsConstructor;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class BidService {

    private static final BidService INSTANCE = new BidService();
    private final CreateBidMapper createBidMapper = CreateBidMapper.getInstance();
    private final BidDao bidDao = BidDao.getInstance();
    private final PlaceBidValidator placeBidValidator = PlaceBidValidator.getInstance();
//    private PlaceBidDto placeBidDto;

    public void placeBid(PlaceBidDto placeBidDto) throws SQLException {
//        placeBidDto = bidDto;

        var validationResult = placeBidValidator.validateData(placeBidDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }

        var betEntity = createBidMapper.mapFrom(placeBidDto);
        bidDao.save(betEntity);
    }

    public List<BidDto> getAllBidByLotId(Integer id) {
        return bidDao.findById(id).stream().map(bidEntity -> BidDto.builder()
                .id(bidEntity.getId())
                .lotName(bidEntity.getLotName())
                .lotId(bidEntity.getLotId())
                .userName(bidEntity.getUserName())
                .userBid(bidEntity.getUserBid())
                .build()).collect(toList());

    }

//    public PlaceBidDto getPlaceBidDto() {
//        return placeBidDto;
//    }

    public static BidService getInstance() {
        return INSTANCE;
    }
}
