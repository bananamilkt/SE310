# WebChatBot

## [ About the Project ]

<span style="opacity: 0.64">A Java web-based customer service chatbot designed to streamline user interactions. Features include post-purchase support, FAQs, locating nearby retailers, and facilitating product purchases directly within the chat interface.</span>

### Built with
[![Eclipse][Eclipse-shield]][Eclipse-install]
[![VSC][VSC-shield]][VSC-install]
![ApacheTomcat][Tomcat-shield]
![Paypal][Paypal-shield]
![GoogleMap][GoogleMap-shield]

[![Languages](https://skillicons.dev/icons?i=java)](https://skillicons.dev)

### Project Manual
- check out more detailed project information `/AssignmentFolder/IndivialProject-COSC310-HAOXIANG-XU.pdf`
- check out the project presentation video `/AssignmentFolder/cosc310project_presentation.mp4`

### Project Structure
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

### Implemented APIs and updates
- Google Geocoding API
   * Geocoding searching for shipping address
   * Auto-complete shipping address
- Google Map JavaScript API
   * nearby electronic store/retailer searching and display by google map
- PayPal API
   * supported for online purchase
- Other updates         
   * new web based GUI
   * Implemented general solution "Flows" to allow for web purchases
   *  Implemented response for nearby retailer searching

## [ Getting Started ]

### Setup Process
- To clone the repository, run the following command `https://github.com/bananamilkt/SE310.git`
- Download models file from `https://1drv.ms/u/s!AidBCfmtc0EDhtZiCroSVeDgESCegg?e=hZWT4m` and put in `SE310\src\main\webapp\WEB-INF\lib` folder
- Import as Dynamic Java web project in Eclipse Java EE
- Run `index.jsp` on Tomcat Server

[VSC-shield]: https://img.shields.io/badge/Visual_Studio_Code-222222?style=for-the-badge&logo=VisualStudioCode&logoColor=FFFFFF&labelColor=007ACC
[VSC-install]: https://code.visualstudio.com/download
[Eclipse-shield]: https://img.shields.io/badge/Eclipse_Ide-222222?style=for-the-badge&logo=EclipseIde&logoColor=FFFFFF&labelColor=2C2255
[Eclipse-install]: https://www.eclipse.org/downloads/packages/release/kepler/sr2/eclipse-ide-java-ee-developers
[Tomcat-shield]: https://img.shields.io/badge/Apache_Tomcat-222222?style=for-the-badge&logo=ApacheTomcat&logoColor=000000&labelColor=F8DC75
[Paypal-shield]: https://img.shields.io/badge/Paypal-222222?style=for-the-badge&logo=Paypal&logoColor=FFFFFF&labelColor=003087
[GoogleMap-shield]: https://img.shields.io/badge/Google_Geocoding_API-222222?style=for-the-badge&logo=GoogleMaps&logoColor=FFFFFF&labelColor=4285F4