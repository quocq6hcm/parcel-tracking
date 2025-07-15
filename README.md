
# 📦 Guest Parcel Tracking System for Hotel Reception
[Swagger UI](http://localhost:9000/swagger-ui/index.html)
## 🧾 Problem Overview

Hotel receptionists often accept parcels for guests staying at the hotel. However, several operational issues arise:
- Parcels are sometimes accepted for guests who have **already checked out**.
- Guests sometimes **forget to pick up their parcels** when checking out.
- Receptionists need a system that can track:
  - Which guests are currently **checked in**
  - Which parcels are **pending pickup**
  - Prevent **accepting parcels** for guests who are no longer staying

---

## 🎯 Goals of the System

- ✅ Accept parcels **only** for guests available at hotel or before checkout
- ✅ List unclaimed parcels at guest **check-out**
- ✅ Prevent accepting parcels for **checked-out** guests
- ✅ Allow guest to pickup parcels during check-out

---

## 🏗️ Architecture Overview

🧩 Services Breakdown
🟢 Guest Service
- GET /guests/{id} — Get guest status 
- GET /guests/search?query — Search by firstName, lastName or socialId
- POST /guests/{id}/check-in — Mark guest as checked in
- POST /guests/{id}/check-out — Mark guest as checked out

📦 Parcel Service
- GET /parcels/{guestId} — View all parcels belong to guest
- POST /parcels — Accept parcel for guest. Only allowed if guest is CheckedIn/InHouse
- POST /parcels/{id}/claim — Mark parcel as claimed

```plaintext
        +------------------------+             +--------------------+
        |      Receptionist      | <---------> |   BFF API Gateway  |
        +------------------------+             +--------------------+
                                                       |
                       ┌───────────────────────────────┴────────────────────────────────────┐
                       |                                                                    |
              +--------------------+                                          +----------------------+     
              |    Guest Service   |                                          |   Parcel Service     |      
              | (Check-In/Out API) |                                          |  (Track & Claim API) |     
              +--------------------+                                          +----------------------+
```
## Guest Forgets to Pick Up Parcels Before Check-Out
- Receptionist searches guest by first name, last name or social id.
- BFF calls GET /guests/{id} to get guest status.
- If status is CheckedIn / InHouse, allow POST /parcels.
- New parcel is accepted, status is Unclaimed

## Parcel Acceptance
- Receptionist searches guest by first name, last name or social id.
- BFF calls GET /guests/{id} to get guest status.
- If status is CheckedIn / InHouse, allow POST /parcels.
- New parcel is accepted, status is Unclaimed

## Show Parcels Available for Pickup at Guest Check-Out
- Receptionist searches guest by first name, last name or social id to proceed check out guests
- BFF calls GET /parcels/{guestId} to get parcels belong to guests and highlight Unclaimed parcels if exist
- Receptionist delivers Unclaimed parcels
- BFF calls POST /parcels/{id}/claim to set parcels status to Claimed
- BFF calls POST /guests/{id}/check-out to set get status
