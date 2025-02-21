import speech_recognition as sr
import pyttsx3
import subprocess

# Initialize components
recognizer = sr.Recognizer()
engine = pyttsx3.init()

# Configure speech settings
voices = engine.getProperty('voices')
engine.setProperty('voice', voices[0].id)  # Change the index to switch voices
engine.setProperty('rate', 150)  # Adjust speech rate

def speak(text, print_text=True):
    if print_text:
        print(f"AI: {text}")
    engine.say(text)
    engine.runAndWait()

def listen_and_recognize():
    with sr.Microphone() as source:
        recognizer.adjust_for_ambient_noise(source)
        print("Listening...")
        audio = recognizer.listen(source)
    try:
        text = recognizer.recognize_google(audio)
        print(f"You said: {text}")
        return text.lower()
    except sr.UnknownValueError:
        speak("Sorry, I didn't catch that.", True)
    except sr.RequestError:
        speak("Speech service unavailable.", True)
    return None

def execute_jarvis_command(command):
    if "chat" in command:
        # Extract query from command
        query = command.replace("chat", "").strip()
        if query:
            speak("Here's the chat response:")
            result = subprocess.run(['java', 'Chat', query], capture_output=True, text=True)
            speak(result.stdout, True)
        else:
            speak("Please provide a topic for chat.")

    elif "weather" in command:
        result = subprocess.run(['java', 'Main', 'weather'], capture_output=True, text=True)
        speak(result.stdout, True)

    elif "open" in command:
        site = command.replace("open", "").strip()
        if site:
            result = subprocess.run(['java', 'Main', f'open {site}'], capture_output=True, text=True)
            speak(result.stdout, True)
        else:
            speak("Please specify a website to open.")

    elif "exit" in command or "quit" in command:
        speak("Goodbye!", True)
        exit()

    else:
        speak("Invalid command. Please try again.")

def main():
    speak("Welcome! How can I assist you today?")
    while True:
        audio = listen_and_recognize()
        if audio:
            execute_jarvis_command(audio)

if __name__ == "__main__":
    main()