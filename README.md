# Paystation
## Team Playstation

### Requirements:

A Paystation is a mechanism which accepts some type of payment in exchange for a limited parking time
based on the amount purchaseby the client. This program emulates that same idea, the paystation program
will do the following:
- Accept nickels, dimes, and quarters as payment by the client
- The bought time will be on display for the client
- Once the client decides to buy a ticket, a receipt will be printed with the transaction history
    - Date, time bought, amount paid, and rate being used 
- The client can cancel which returns the number of each coin entered and the total coins value
- The paystation has three functioning rate strategies for three different clients
  1. AlphaTown uses Linear strategy: 
    - amount of time = (amount inserted * 2) / 5
  2. BetaTown uses Progressive strategy:
    - Time < One hour: amount of time = (amount inserted * 2) / 5
    - One hour < Time < Two hours, or 150 <= amount inserted < 350: amount of time = (amount inserted - 150) * (3 / 10) + 60
    - Two hours < Time: amount of time = (amount inserted - 350) / 5 + 120
  3. GammaTown uses Alternating strategy:
    - On weekdays: Linear rate strategy
    - On weekends: Progressive rate strategy
    
We as a team decided it would be best to implement the "Polymorphic Proposal" that we talked about in class for our rate strategies for maintainability, readability, and reliability. Using the "Polymorphic Proposal," we could use one main project with additional subclasses, therefore it was easier to maintain than using the "Source Tree Copying." It was also easier to read than using many conditional statements as seen in the "Parametric Solution." Overall it was seemed to be the most reliable of the available options too once we started our UML. Therefore we built a well-maintained, readable, and reliable code using the "Polymorphic Proposal."
    
### Team Work:

  The team consisted of four people; Brian, Mike, Christian and Jose. The work was divided up by
  where people better suited their strengths. Mike had more experience with UML Class diagrams 
  than the rest of the team so he laid out the basic plan on how the team would first approach the 
  paystation, the plan was open to editing during the process if certain things needed to be adjusted.
  Jose went forward with some code creating the classes for two out of three rate strategies, Linear and
  Progressive rate strategies and creating a couple of tests for them as well. Christian worked on
  the more difficult rate strategy, Alternating rate strategy. For more specific details about the rate 
  strategies please look back at requirements section. Brian would be in charge of the rest 
  of testing scenarios, both creating and testing out multiple tests to comfirm the paystation
  was functioning accordingly more testing discussed below in testing section. Mike coded a GUI (graphic user interface) 
  so the client had a better way to interact with the paystation for the main. The PayStation GUI consist 
  of a display and buttons, three buttons representing a nickel, dime, or quarter payment. As the client 
  pushes these buttons the amount the client is paying is getting totaled and the amount of time the client
  is buying is presented of the display. After reaching the amount of time the client wants, they either 
  can push a buy button which prints a receipt with the following information: name of town, date, amount spent,
  and the time bought. The client can also cancel by pushing the cancel button which once pushed returns
  the exact amount of coins inserted back to the client, example 3 dimes and 4 nickels are inserted which
  equals 50 cents if clients pushes cancel the paystation will return 3 dimes and 4 nickels and not 2 quarters
  or another combination of coins. There is also a config button which allows the user to switch between the 
  different rate strategies, and then displays the name of rate strategy on the paystation, Alphatown, BetaTown, and GammaTown. 
  
  
### Testing: 

  We performed unit testing on our code. We made individual tests for each of the rate strategies - Linear, Progressive, and Alternating that verified whether our program calculated the expected amount of time. We employed automated testing using the JUnit testing framework in order to test whether our rate strategies worked correctly. The intuition behind our test design was to check the endpoints of each interval of time calculation for amount of cents entered, as well as a point inside the interval. This helps determine whether the rate is working as according to plan. One caveat is still that you could park for infinitely much time while using a paystation, and thus it doesn’t give closure to these defined intervals. To remedy this, we thought it would be sufficient to check a reasonably large amount of cents for its corresponding time. Using this methodology to test our program was logical, straightforward, and beneficial to us finding bugs.
  We also tested the receipt implementation in order to verify that the receipt object stored the correct amount of cents entered as well as amount of time purchased. This helped ensure that our program didn't have any intermediate bugs. As previously specified in the teamwork section, Brian mainly wrote the tests, however Christian and Jose were responsible for the rate strategies. The tests were all written after the code for this lab. This did not impact the team's ability to write working code for the paystation.
