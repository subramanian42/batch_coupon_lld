package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Batch {
    UUID batchId;
    String batchName;
    String distributor;
    LocalDate startDate;
    LocalDate endDate;
    CouponType couponType;
    BatchState state;
    List<Coupon> coupon;

    public Batch(UUID batchId, String batchName, LocalDate startDate, LocalDate endDate, CouponType couponType,
            String distributor) {
        this.batchId = batchId;
        this.batchName = batchName;
        this.distributor = distributor;
        this.startDate = startDate;
        this.endDate = endDate;
        this.couponType = couponType;
        this.state = BatchState.CREATED;
        this.coupon = new ArrayList<>();
    }

    public UUID getBatchId() {

        return batchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public String getDistributor() {
        return distributor;
    }

    public BatchState getState() {

        return state;
    }

    public List<Coupon> getCoupon() {
        return coupon;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setState(BatchState state) {
        this.state = state;
    }

    public void addCoupon(String code, CouponType couponType) {
        Coupon coupon = new Coupon(code, couponType);
        this.coupon.add(coupon);
    }

    public void displayBatchInfo() {
        System.out.println(batchName + " " + state);
    }

}
