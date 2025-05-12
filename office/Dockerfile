FROM nginx
ENV TZ 'Asia/Shanghai' 
ENV LANG C.UTF-8 
COPY dist/ /usr/share/nginx/html/
COPY nginx/default.conf /etc/nginx/conf.d/default.conf
COPY nginx/localhost.crt /etc/nginx/nginx.crt
COPY nginx/localhost.key /etc/nginx/nginx.key
RUN echo 'echo init ok!'
