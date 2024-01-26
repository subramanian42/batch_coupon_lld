package services;

import java.util.UUID;

import Repository.BatchRepository;
import entities.Batch;
import entities.CouponType;

public class CouponService {
    private BatchRepository batchRepository;

    public CouponService(BatchRepository batchRepository) {
        this.batchRepository = batchRepository;
    }

    public void ingestCoupon(UUID batchId, CouponType type, int couponCount, String... coupons) {
        if (batchId == null) {

            throw new IllegalArgumentException("invalid batch id");
        }
        Batch batch = batchRepository.getBatch(batchId);

        switch (type) {
            case OPEN:
                batch.addCoupon(coupons[0], type);
                break;
            case CLOSED:
                for (String coupon : coupons)
                    batch.addCoupon(coupon, type);
                break;
            default:
                throw new IllegalArgumentException("invalid coupon type");
        }
    }
}
