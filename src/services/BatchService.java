package services;

import java.time.LocalDate;
import java.util.UUID;
import Repository.BatchRepository;
import entities.Batch;
import entities.BatchState;
import entities.CouponType;

public class BatchService {
    BatchRepository repository;

    public BatchService(BatchRepository repository) {
        this.repository = repository;
    }

    public UUID addBatch(String batchName, LocalDate startDate, LocalDate endDate, CouponType couponType,
            String distributor) {
        UUID batchId = UUID.randomUUID();
        Batch batch = new Batch(batchId, batchName, startDate, endDate, couponType, distributor);
        repository.addBatch(batch);
        System.out.println("BATCH_ADDED " + batchId);
        return batchId;
    }

    public Batch getBatch(UUID batchId) {
        Batch batch = repository.getBatch(batchId);
        checkExpiryDate(batch);
        batch.displayBatchInfo();
        return batch;
    }

    private void checkExpiryDate(Batch batch) {
        if (!(batch.getStartDate().isBefore(LocalDate.now()) && batch.getEndDate().isAfter(LocalDate.now()))) {
            batch.setState(BatchState.EXPIRED);
        }
    }

    public BatchState updateState(BatchState newState, UUID batchId) {
        Batch batch = repository.getBatch(batchId);
        if (newState == batch.getState()) {
            throw new IllegalArgumentException("Batch is already " + batch.getState());
        }

        switch (newState) {
            case ACTIVE:
                if (batch.getState() != BatchState.APPROVED) {
                    System.out.println("CURRENT_STATE " + batch.getState());
                    return batch.getState();
                } else
                    break;
            case TERMINATED:
                if (batch.getState() == BatchState.SUSPENDED) {
                    System.out.println("CURRENT_STATE " + batch.getState());
                    return batch.getState();
                } else
                    break;

            default:
                break;
        }
        batch.setState(newState);
        System.out.println("STATE_CHANGED " + newState);
        return newState;
    }

}
