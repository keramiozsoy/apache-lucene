# apache-lucene

https://github.com/apache/lucene


## endpoints

http://localhost:8080/swagger-ui/index.html



##
curl -G "http://localhost:8080/example/0/defaultNormalize" --data-urlencode "text=The -LAZY cat'."


##

curl -G "http://localhost:8080/example/1/lowercase" --data-urlencode "text=The -LAZY cat'."

## 

curl -G "http://localhost:8080/example/2/deletePunctuation" --data-urlencode "text=The -LAZY cat'."

## 

curl -G "http://localhost:8080/example/3/lowercaseAndDeletePunctuation" --data-urlencode "text=The -LAZY cat'."

##

