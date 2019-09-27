import React, {Component} from 'react';
import {Card, CardImg, CardImgOverlay, CardText, CardBody, CardTitle} from 'reactstrap';

class Dishdetail extends Component {

    constructor(props){
        super(props);

        console.log('Dishdetail component constructor is invoked');
    }

    render(){

        const dish = this.props.dish;
        if(dish != null){
        return (
        <div className="container">
            <div className="row">
                    <div className = "col-12 col-md-5 m-1">
                        {this.renderDish(dish)}
                    </div>
                    <div className="col-12 col-md-5 m-1">
                        <h4>Comments</h4>
                        <ul className="list-unstyled">
                        {this.renderComments(dish)}
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

    renderDish(dish){
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
    renderComments(dish){
    const options = { year: 'numeric', month: 'short', day: 'numeric' };
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
}


export default Dishdetail;