# LLD-203 Exercise Batch Coupon System

## Problem Statement

Create and implement a coupon management system that allows any distributor to generate a batch and add coupons to it. Subsequently, the system will allocate coupons from the batch to users. In essence, it's a top-notch platform for distributing coupons.

### Terms

- **Batch:** A collection of coupons with identical attributes, associated with a specific distributor. Coupons from each batch will be distributed to users independently.
- **Coupon:** An entitlement that allows users to redeem discounts or benefits from a specific batch. Each batch of coupons shares the same characteristics.

### Requirements

#### System should able to create batches with following attributes

- batchState
- validityPeriod
- distributor
- couponType

#### System should able to manage batch states

- A batch initially begins in the "CREATED" state
- A state machine should be established to manage allowed transitions for a batch.
- An approval process should exist, enabling the transition of a batch to an "APPROVED" state.
- A designated state, "ACTIVE," should be available to mark a batch as active.
- A batch should automatically transition to the "EXPIRED" state once its validity period is over.
- A suspension state is required to allow distributors to temporarily suspend coupon distribution from a batch. This suspended state can later be resumed.
- A termination state is necessary to allow distributors to permanently halt coupon distribution from a batch.

#### System should have capability of time bound match

- Batches should be time-bound and remain active within their specified duration. They must automatically expire after the end date and become active only after the start date.

#### The system must support both "OPEN" (generic codes for all users) and "CLOSED" (unique codes for each user) coupon types.

### APIs

1. **ADD_BATCH**

    - **Endpoint:** `/add_batch`
    - **Method:** POST
    - **Description:** Adds batch information to the platform
    - **Input Format:** JSON
        ```json
        {
          "batch_name": "Diwali",
          "distributor": "crio",
          "start_validity_date": "2023-11-10",
          "end_validity_date": "2023-12-10",
          "couponType": "OPEN"
        }
        ```
    - **Output Format:** JSON
        ```json
        {
          "batch_id": 1
        }
        ```

2. **INGEST_COUPON**

    - **Endpoint:** `/ingest_coupon`
    - **Method:** POST
    - **Description:** Ingests coupons to the batch. If the coupon type is OPEN, then there will be only one coupon code. If the coupon type is CLOSED, there will be <number_of_coupons> coupon codes.
    - **Input Format:** JSON
        ```json
        {
          "batch_id": 1,
          "coupon_type": "OPEN",
          "number_of_coupons": 10,
          "coupon_codes": ["CRIOTECH"]
        }
        ```
    - **Output Format:** JSON
        ```json
        {
          "batch_name": "Diwali"
        }
        ```

3. **UPDATE_STATE**

    - **Endpoint:** `/update_state`
    - **Method:** POST
    - **Description:** Updates the state of the batch. Prints the current state if unable to update.
    - **Input Format:** JSON
        ```json
        {
          "batch_id": 1,
          "state": "APPROVED"
        }
        ```
    - **Output Format:** JSON
        ```json
        {
          "state": "APPROVED"
        }
        ```
        OR
        ```json
        {
          "current_state": "SUSPENDED"
        }
        ```

4. **GET_BATCH**

    - **Endpoint:** `/get_batch`
    - **Method:** GET
    - **Description:** Gets batch information for the user.
    - **Input Format:** Query Parameter
        ```
        batch_id=1
        ```
    - **Output Format:** JSON
        ```json
        {
          "batch_name": "Diwali",
          "batch_current_state": "SUSPENDED"
        }
        ```


| Input                                        | Output                      |
|----------------------------------------------|-----------------------------|
| ADD_BATCH,Diwali,crio,2023-11-10,2023-12-10,OPEN | BATCH_ADDED 1               |
| ADD_BATCH,BBD,flipkart,2023-09-10,2023-11-10,CLOSED | BATCH_ADDED 2               |
| INGEST_COUPON,1,OPEN,10,CRIOTECH               | COUPONS_ADDED Diwali        |
| INGEST_COUPON,2,CLOSED,4,BBD-1,BDD-2,BBD-3,BBD-4 | COUPONS_ADDED BBD           |
| UPDATE_STATE,1,APPROVED                        | STATE_CHANGED APPROVED      |
| UPDATE_STATE,2,ACTIVE                          | CURRENT_STATE CREATED       |
| UPDATE_STATE,1,SUSPENDED                       | STATE_CHANGED SUSPENDED     |
| UPDATE_STATE,1,TERMINATED                      | CURRENT_STATE SUSPENDED     |
| GET_BATCH,1                                   | Diwali SUSPENDED            |
| GET_BATCH,2                                   | BBD CREATED                 |

### Guidelines

- Input can be read from a file or STDIN or coded in a driver method. [No API and No UI]
- No need to match the output response exactly mentioned in the commands. It is just for examples.
- Output can be written to a file or STDOUT. [No API]
- Store all interim/output data in-memory data structures. The usage of databases is not allowed.
- Restrict internet usage to looking up syntax.
- Language should be Java or Python only.

### Expectations

- The code should be demo-able (very important). The code should be functionally correct and complete.
- At the end of this interview round, an interviewer will provide multiple inputs to your program for which it is expected to work
- The code should handle edge cases properly and fail gracefully. Add suitable exception handling, wherever applicable.
- The code should be readable, modular, testable, and extensible. Use intuitive names for your variables, methods, and classes.
- It should be easy to add/remove functionality without rewriting a lot of code.
- Do not write a monolithic code.
- Don’t use any databases.