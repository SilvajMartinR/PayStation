# Paystation
Team Playstation

Paystation requirements are as follows:
- Must accept nickels, dimes, and quarters as payment
- Must show the time bought on the display
- Must print a parking time recipt after buying a parking ticket
- Must print the total coin values returned and the number of each coin type after canceling a payment
- Must contain three functioning rate strategies for three different clients
  1. Linear: (amount inserted * 2) / 5
  2. Progressive:
    - Time < One hour: (amount inserted * 2) / 5
    - One hour < Time < Two hours, or 150 <= amount inserted < 350: (amount inserted - 150) * (3 / 10) + 60
    - Two hours < Time: (amount inserted - 350) / 5 + 120
  3. Alternating:
    - On weekdays: Linear rate strategy
    - On weekends: Progressive rate strategy
