# XmenApi
API test with grails to know if an DNA is mutant

example request:
```
Method: POST
URL: https://mutantdetectorapi.herokuapp.com/analyzer/index
Data-Type: text/json
Data: ' ["ATGCGAA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"] '
```

example python script:

```
>>> import request
>>> import json

>>> r = requests.post("https://mutantdetectorapi.herokuapp.com/analyzer/index", json=['AAAA', 'AAAA', 'AAAA', 'AAAA'])
>>> r
<Response [200]>
>>> r.text
'{"results":"true","status":"OK"}'
>>> 

```
