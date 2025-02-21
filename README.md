AI Voice Assistant with Java & Python
This project is an AI-powered voice assistant that integrates Java and Python to perform tasks such as weather updates, web browsing, and chatbot interactions. The system uses speech recognition to process user commands and execute appropriate actions.

Features
🎤 Speech Recognition: Listens to user commands and processes them.
🗣 Text-to-Speech (TTS): Responds using an AI-generated voice.
🌦 Weather Updates: Fetches real-time weather data using an API.
🌐 Website Opener: Opens websites by name.
🤖 Chatbot: Uses an AI model to respond to user queries.

Technologies Used
Python (speech recognition, text-to-speech)
Java (API handling, web automation, chatbot)
Flask (for potential backend integration)
Weather API (for fetching real-time weather data)

Project Structure
/src
 ├── speech_and_speak.py  # Python script for speech recognition & TTS
 ├── WeatherUpdate.java   # Java class for weather API integration
 ├── OpenWebsiteByName.java # Java class to open websites via system browser
 ├── Main.java            # Central controller for executing commands
 ├── Chat.java            # Chatbot using AI API
 ├── MainActivity.java    # Android UI component (if applicable)
Installation & Usage

Clone this repository: https://github.com/haseeb676-sir7/AI-Voice-Assistant-.git

git clone https://github.com/haseeb676-sir7/AI-Voice-Assistant-.git
cd AI-Voice-Assistant
Install Python dependencies:

pip install speechrecognition pyttsx3
Compile Java files:

javac *.java
Run the Python script:python speech_and_speak.py
Give voice commands to interact with the assistant.


Future Enhancements
✅ Add AI-powered natural language processing (NLP)
✅ Enhance chatbot responses using a local AI model
✅ Improve UI/UX for better interaction
