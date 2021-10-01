import speech_recognition as sr
from pynput.keyboard import Controller
from pygame.mixer import mixer
import time

class Main:
    def __init__(self):
        self.board = Controller()
        print("Started!\n\n")
        self.mixer = mixer.
        while True:
            query = self.take_command()
            print("TYPING...\n")
            for i in query: 
                self.board.type(i)
                time.sleep(0.01)
            self.board.type(" ")

    def take_command(self):
        try:
            r = sr.Recognizer()
            with sr.Microphone() as source:
                r.adjust_for_ambient_noise(source)
                print("Listenting...\n")
                audio = r.listen(source, 100, 50)
                print("Recognizing.... \n")
                query2 = r.recognize_google(audio, language='en-IN')
                query1 = str(query2)
                print("You Said: ", query1, "\n\n")
            return query1
        except Exception as e:
            print(e)
            return ""
    
if __name__ == "__main__":
    Main()