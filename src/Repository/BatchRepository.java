package Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import entities.Batch;

public class BatchRepository {
    Map<UUID, Batch> batches = new HashMap<>();

    public void addBatch(Batch batch) {
        batches.put(batch.getBatchId(), batch);
    }

    public Batch getBatch(UUID batchId) {
        return batches.get(batchId);
    }

}
