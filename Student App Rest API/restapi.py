from flask import Flask,request,jsonify
from pymongo import MongoClient

app=Flask(__name__)
con=MongoClient()
db=con.svce
studa=db.student
studb=db.cat
studc=db.attendance
studd=db.tt

@app.route("/stud/<rno>",methods=['GET'])
def validate(rno):
    stud = studa.find_one({"rno": rno})
    if stud:
        output = {"name": stud["name"], "rno": stud["rno"], "sec": stud["sec"], "dep": stud["dep"], "yr": stud["yr"]}
    else:
        output = "Not Found"

    return jsonify({"result": output})

@app.route('/stud',methods=['POST'])
def val():
    rnoval={'rno':request.json['rno']}
   # print(rnoval)
    print("REQUESTED")
    passval = {'pass': request.json['pass']}
   # print(passval)
    stud = studa.find_one({"rno": rnoval['rno'], "passw": passval['pass']})
    if stud:
        output = {"name": stud["name"], "rno": stud["rno"], "sec": stud["sec"], "dep": stud["dep"], "yr": stud["yr"]}
      #  print(output)
        return jsonify({"result": output}), 201
    else:
        output = "Not Found"
        return jsonify({"result": output}), 401



@app.route("/time/<rno>/<day>",methods=['GET'])
def timetable(rno,day):
    stud3 = studa.find_one({"rno": rno})
    if stud3:
       secval= stud3["sec"]
       depval= stud3["dep"]
       yrval= stud3["yr"]
       stud4=studd.find_one({"sec": secval,"dep": depval,"yr": yrval,"day": day})
       if stud4:
           output = {"hour1": stud4["hour1"], "hour2": stud4["hour2"], "hour3": stud4["hour3"],
                     "hour4": stud4["hour4"], "hour5": stud4["hour5"], "hour6": stud4["hour6"], "hour7": stud4["hour7"]}
       else:
           output = "Not Found"
    else:
        output = "Not Found"

    return jsonify({"result": output})


@app.route("/cat/<rno>",methods=['GET'])
def catmark(rno):
    stud1 = studb.find_one({"rno": rno})
    if stud1:
        output = {"cs6601a": stud1["cs6601a"], "cs6602a": stud1["cs6602a"], "cs6603a": stud1["cs6603a"],"cs6604a": stud1["cs6604a"],
                  "cs6605a": stud1["cs6605a"],"cs6606a": stud1["cs6606a"],"cs6601t": stud1["cs6601t"], "cs6602t": stud1["cs6602t"],
                  "cs6603t": stud1["cs6603t"],"cs6604t": stud1["cs6604t"],"cs6605t": stud1["cs6605t"],"cs6606t": stud1["cs6606t"]}
    else:
        output = "Not Found"

    return jsonify({"result": output})


@app.route("/att/<rno>",methods=['GET'])
def attendancepercent(rno):
    stud2 = studc.find_one({"rno": rno})
    if stud2:
        output = {"cs6601a": stud2["cs6601a"], "cs6602a": stud2["cs6602a"], "cs6603a": stud2["cs6603a"],"cs6604a": stud2["cs6604a"],
                  "cs6605a": stud2["cs6605a"], "cs6606a": stud2["cs6606a"], "cs6601t": stud2["cs6601t"], "cs6602t": stud2["cs6602t"],
                  "cs6603t": stud2["cs6603t"], "cs6604t": stud2["cs6604t"], "cs6605t": stud2["cs6605t"], "cs6606t": stud2["cs6606t"]}
    else:
        output = "Not Found"

    return jsonify({"result": output})




if __name__=="__main__":
    app.run(host="192.168.1.2",debug=True,port=8080)