import easyocr
import numpy
import cv2
import sys
import io
import random
import matplotlib.pyplot as plt
from PIL import ImageFont, ImageDraw, Image
from googletrans import Translator
sys.stdout = io.TextIOWrapper(sys.stdout.detach(), encoding = 'utf-8')
sys.stderr = io.TextIOWrapper(sys.stderr.detach(), encoding = 'utf-8')

# 파일명: easyocr_script
# 작성자: 김도연
# 설명 : 이미지 ocr

# 번역 후 글씨가 있는 곳에 흰 박스를 씌우고 그 위에 글씨를 씌우는 코드
# 검정색 글씨에 흰색 테투리를 입힘

image_path = sys.argv[1]

# OCR을 위한 EasyOCR 리더 생성
reader = easyocr.Reader(['ko', 'en'])

# 이미지에서 텍스트를 읽어옴
result = reader.readtext(image_path)

#얘는 글자만 출력
for detection in result:
    text = detection[1]
    print(text)


# Google Translate API 초기화
translator = Translator()

# 이미지 로드
img = cv2.imread(image_path)

# OpenCV 배열을 PIL 이미지로 변환
img = Image.fromarray(cv2.cvtColor(img, cv2.COLOR_BGR2RGB))
draw = ImageDraw.Draw(img)

# 글자 덮어씌우기
for detection in result:
    # 텍스트 위치와 내용 가져오기
    top_left = tuple(map(int, detection[0][0]))
    bottom_right = tuple(map(int, detection[0][2]))
    text = detection[1]

    # EasyOCR로 인식한 글자의 크기 계산
    text_width = bottom_right[0] - top_left[0]
    text_height = bottom_right[1] - top_left[1]

    # 번역을 수행
    translation = translator.translate(text, src='ko', dest='en').text

    # 글자 덮어씌우기
    font_size = int(min(text_width, text_height) * 0.5)  # 글자 크기 조절
    font = ImageFont.truetype("fonts/HMKMRHD.TTF", font_size)
    draw.rectangle([top_left, bottom_right], fill="white")
    draw.text(top_left, translation, font=font, fill="black", stroke_width=3, stroke_fill="white")

# 이미지 경로를 변경
new_image_path = image_path.replace('uploads', 'trans')
#new_image_path = new_image_path.replace('.png', '_trans.png')

# 이미지 저장
img.save(new_image_path)
