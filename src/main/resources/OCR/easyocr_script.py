import easyocr
import numpy
import cv2
import sys
import io
import random
import matplotlib.pyplot as plt
from PIL import ImageFont, ImageDraw, Image
sys.stdout = io.TextIOWrapper(sys.stdout.detach(), encoding = 'utf-8')
sys.stderr = io.TextIOWrapper(sys.stderr.detach(), encoding = 'utf-8')

#추가: 받은 사진을 ocr하기 위해서.
image_path = sys.argv[1]

reader = easyocr.Reader(['ko', 'en'])
# need to run only once to load model into memory
#result = reader.readtext('src/main/resources/b.png')
result = reader.readtext(image_path)


#얘는 글자만 출력
for detection in result:
    text = detection[1]
    print(text)

# cv2.imshow("test",img)
# cv2.waitKey(0)
