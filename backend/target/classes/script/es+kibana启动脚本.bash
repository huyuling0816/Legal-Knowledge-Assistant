# 安装 es 7.16.2，注意版本不要更换
docker pull elasticsearch:7.16.2
docker run --name elasticsearch -p 9200:9200 -p 9300:9300 \
-e "discovery.type=single-node" \
-e ES_JAVA_OPTS="-Xms128m -Xmx512m" \
-v /mydockerdata/es/config/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml \
-v /mydockerdata/es/data:/usr/share/elasticsearch/data \
-v /mydockerdata/es/plugins:/usr/share/elasticsearch/plugins \
-d elasticsearch:7.16.2

# 安装 kibana 7.16.2，注意版本不要更换
docker pull kibana:7.16.2
docker run --name mykibana  -p 5601:5601 \
-v /mydockerdata/kibana/elk/kibana.yml:/usr/share/kibana/config/kibana.yml \
-d kibana:7.16.2