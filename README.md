# dogpark

## Link: https://dog-park-micro.herokuapp.com/

## Dogpark
GET dogpark (by id)
https://dog-park-micro.herokuapp.com/api/v1/dog_park/id/XXXX

GET dogpark (by name) 
https://dog-park-micro.herokuapp.com/api/v1/dog_park/name/XXXX

GET dogparks (all)
https://dog-park-micro.herokuapp.com/api/v1/dog_park/all

POST dogpark 
https://dog-park-micro.herokuapp.com/api/v1/dog_park/?name=XXXX&longitude=XXXX&latitude=XXXX

{ "name": "Not blank",
  "longitude": Only positives,
  "latitude": Only positives
  }
  
PUT dogpark (by id)
https://dog-park-micro.herokuapp.com/api/v1/dog_park/update/?id=XX

DELETE dogpark (by id)
https://dog-park-micro.herokuapp.com/api/v1/dog_park/delete/?id=XX

## Review

GET review (all)
https://dog-park-micro.herokuapp.com/api/v1/review/all

GET review (by id)
https://dog-park-micro.herokuapp.com/api/v1/review/id/XXXX

POST review
https://dog-park-micro.herokuapp.com/api/v1/review/
{ "comment": "XXXX", 
"rating": 0-5,
"dogpark_id": Not null, Only positives
}

DELETE review (by id)
https://dog-park-micro.herokuapp.com/api/v1/review/delete/?id=XX

