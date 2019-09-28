import React from 'react';
import {Card, CardImg, CardText, CardBody, CardTitle} from 'reactstrap';


    const Dishdetail = (props)=>{
        console.log('Dishdetail  component did render is invoked');

        const dish = props.dish;
        if(dish != null){
        return (
        <div className="container">
            <div className="row">
                    <div className = "col-12 col-md-5 m-1">
                        <RenderDish dish = {props.dish}/>
                    </div>
                    <div className="col-12 col-md-5 m-1">
                        <h4>Comments</h4>
                        <ul className="list-unstyled">
                        <RenderComments dish={props.dish} />
                        </ul>
                    </div>
                </div>
            </div>
        );
        }
        else {
            return (
            <div></div>
            );
        }


    }

    function RenderDish({dish}){
        return(
        <Card>
           <CardImg width="100%" src={dish.image} alt= {dish.name}/>
           <CardBody>
                <CardTitle>{dish.name}</CardTitle>
                <CardText>{dish.description}</CardText>
           </CardBody>
        </Card>

        );

    }
   function RenderComments({dish}){
    if(dish.comments == null || dish.comments.length < 1){
        return(
            <div></div>
        );
    }

    return(dish.comments.map((post) =>
                               <div key={post.id} className="m-1">
                                   <li >{post.comment} </li>
                                   <li >--{post.author},  {new Intl.DateTimeFormat('en-US',{year:'numeric', month:'short',day:'2-digit'}).format(new Date(Date.parse(post.date)))} </li>
                               </div>

                           ));

    }


export default Dishdetail;