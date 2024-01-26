import java.time.LocalDate;
import java.util.UUID;

import Repository.BatchRepository;

import entities.BatchState;
import entities.CouponType;
import services.BatchService;
import services.CouponService;

public class App {
    public static void main(String[] args) throws Exception {
        BatchRepository repo = new BatchRepository();
        BatchService batchService = new BatchService(repo);
        CouponService couponService = new CouponService(repo);

        UUID batch1 = batchService.addBatch("Batch1", LocalDate.of(2024, 1, 10), LocalDate.of(2024, 8, 10),
                CouponType.OPEN, "flipKart");
        UUID batch2 = batchService.addBatch("Batch2", LocalDate.of(2024, 1, 12), LocalDate.of(2024, 1, 25),
                CouponType.CLOSED, "amazon");
        couponService.ingestCoupon(batch1, CouponType.OPEN, 0, "flk1");
        couponService.ingestCoupon(batch2, CouponType.CLOSED, 3, "amz1", "amz2", "amz3");

        batchService.updateState(BatchState.ACTIVE, batch1);
        batchService.updateState(BatchState.APPROVED, batch1);
        batchService.updateState(BatchState.ACTIVE, batch1);
        batchService.updateState(BatchState.SUSPENDED, batch1);
        batchService.updateState(BatchState.TERMINATED, batch1);
        batchService.updateState(BatchState.ACTIVE, batch1);
        batchService.updateState(BatchState.TERMINATED, batch1);
        batchService.getBatch(batch1);
        batchService.getBatch(batch2);

    }
}
