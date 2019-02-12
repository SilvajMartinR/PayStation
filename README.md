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
    
Team Work:

  The team consisted of four people; Brian, Mike, Christian and Jose. The work was divided up by
  where people better suited their strengths. Mike had more experience with UML Class diagrams 
  than the rest of the team so he laid out the basic plan on how the team would first approach the 
  paystation, the plan was open to editing during the process if certain things needed to be adjusted.
  Jose went forward with some code creating the classes for two out of three rate strategies, Linear and
  Progressive rate strategies and creating a couple of tests for them as well. Christian worked on
  the more difficult rate strategy, Alternating rate strategy. Brian would be in charge of the rest 
  of testing scenarios, both creating and testing out multiple tests to comfirm the paystation
  was functioning accordingly. Mike coded a GUI (graphic user interface) so the client had a
  better way to interact with the paystation for the main. Christian and Jose have been working on the
  documentation part of the paystation. 
  
  
Testing: 

