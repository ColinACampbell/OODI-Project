// services are used to make http requests

export default {
    async login (info) {
        // fetch post request
        const { email, password } = info;
        let res =  await fetch(`${process.env.VUE_APP_API_ENDPOINT}/api/user/login`,
            {
                method: "POST",
                body: JSON.stringify({
                    email,
                    password,
                }),
                headers: {
                    "Content-Type": "application/json"
                },
                
            })

        let data = await res.json()
        if(res.status !== 200){
            return "Invalid login"
        }else{
            // console.log(data)
            return data
        }
       
    }, 

    async signup (info) {
        // fetch post request
        let { name, email, position } = info

        if(position === "Client"){
            if(name.slice(0,3) !== "IPP"){
                return "Invalid"
            }
        } else {
            if(!email.includes("@itspixelperfect.com")){
                return "Invalid"
            }
        }

        console.log(info)

        let res = await fetch(`${process.env.VUE_APP_API_ENDPOINT}/api/user`,
            {
                method: "POST",
                headers: {
                    'content-type': 'application/json',
                },
                body: JSON.stringify(info)
            }
        )
        
        
        return res.status
    }, 
}