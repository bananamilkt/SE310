### How to run code
#### Individual Project
- Pull repository from commit ID: https://github.com/bananamilkt/SE310.git
- Download models file from https://1drv.ms/u/s!AidBCfmtc0EDhtZiCroSVeDgESCegg?e=hZWT4m and put in SE310\src\main\webapp\WEB-INF\lib folder
- Import as Dynamic Java web project in Eclipse Java EE
- Run index.jsp on Tomcat Server
- check out more detailed project information at /AssignmentFolder/IndivialProject-COSC310-HAOXIANG-XU.pdf

### Documentation
#### Class Structure
* Chatbot
    * Main method for the bot program. Once instance is created, this class handles the processing of the user input
* GUI
    * Creates a GUI and passes user input into the Chatbot class for processing (Still exist, but no longer use)
* Response
    * The "response" object
    * Stores information of a certain response that the chatbot could provide to the user
* Flow
    * The "flow" object
    * Sequence of responses that ask the user input
    * Stores information on a certain series of messages that must be entered in a specific order
    * Takes care of situations where multiple lines of user input is needed to be stored or processed at the same time
* Dict
    * Handles correcting spelling of inputs
    * Search synonyms in dict for words
    * Detect place of speech for words
* SentimentAnalysis
    * Handles analysing user input for sentiment when required
* Geocoding
    * using Google geocoding for location targeting
* Testing
    * Unit tests
#### New features
* new web based GUI
* Implemented general solution "Flows" to allow for web purchases
* PayPal supported for web purchase
* Auto-complete shipping address
* Geocoding searching for shipping address
* nearby electronic store/retailer searching and display by google map
* check out more detailed project information at /AssignmentFolder/IndivialProject-COSC310-HAOXIANG-XU.pdf
