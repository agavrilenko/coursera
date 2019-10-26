import React, {Component} from 'react';
import {Card, CardImg, CardText, CardBody, CardTitle, Breadcrumb, BreadcrumbItem,
 Nav, NavItem,Button, Modal, ModalHeader, ModalBody,
Label, Col, Row} from 'reactstrap';

import {Link } from 'react-router-dom';
import {Control, LocalForm, Errors} from 'react-redux-form';
import { Loading } from './LoadingComponent';
import { baseUrl } from '../shared/baseUrl';
import { FadeTransform, Fade, Stagger } from 'react-animation-components';

const required = (val) => val && val.length;
const maxLength = (len) => (val) => !(val) || (val.length <= len);
const minLength = (len) => (val) => val && (val.length >= len);

    class DishDetail extends Component{

    constructor(props){
        super(props);
    }

    render(){

            console.log('Dishdetail  component did render is invoked');

                const dish = this.props.dish;

     if (this.props.isLoading) {
                return(
                    <div className="container">
                        <div className="row">
                            <Loading />
                        </div>
                    </div>
                );
            }
            else if (this.props.errMess) {
                return(
                    <div className="container">
                        <div className="row">
                            <h4>{this.props.errMess}</h4>
                        </div>
                    </div>
                );
            }
            else if(dish != null){
                return (
                <div className="container">
                    <div className="row">
                        <Breadcrumb>
                            <BreadcrumbItem><Link to='/menu'>Menu</Link></BreadcrumbItem>
                            <BreadcrumbItem active>{this.props.dish.name}</BreadcrumbItem>
                        </Breadcrumb>
                        <div className="col-12">
                            <h3>{this.props.dish.name}</h3>
                            <hr />
                        </div>
                    </div>

            <div className="row">
                        <RenderDish dish = {this.props.dish}/>

                        <RenderComments comments={this.props.comments}
                         postComment={this.props.postComment}
                        dishId = {this.props.dish.id}                     />

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

    }

    function RenderDish({dish}){
        return(
        <div className="col-12 col-md-5 m-1">
        <FadeTransform in
                        transformProps={{
                            exitTransform: 'scale(0.5) translateY(-50%)'
                        }}>
            <Card>
               <CardImg top src={baseUrl + dish.image} alt={dish.name} />
               <CardBody>
                    <CardTitle>{dish.name}</CardTitle>
                    <CardText>{dish.description}</CardText>
               </CardBody>
            </Card>
        </FadeTransform>
        </div>

        );

    }
   function RenderComments({comments, postComment, dishId}) {
    if(comments == null || comments.length < 1){
        return(
            <div></div>
        );
    }

    const commts = comments.map((post) =>
                            <Fade key={post.id} in>
                               <div  className="m-1">
                                   <li >{post.comment} </li>
                                   <li >--{post.author},  {new Intl.DateTimeFormat('en-US',{year:'numeric', month:'short',day:'2-digit'}).format(new Date(Date.parse(post.date)))} </li>
                               </div>
                            </Fade>

                           );

    return(
        <div className="col-12 col-md-5 m-1">
            <h4>Comments</h4>
            <ul className="list-unstyled">
                <Stagger in>
                    {commts}
                 </Stagger>
            </ul>
             <CommentForm dishId={dishId} postComment={postComment} />
        </div>
    );

    }


    class CommentForm extends Component {
        constructor(props){
            super(props);
            this.state = {
                isModalOpen: false
            };
            this.updateCommentFormState = this.updateCommentFormState.bind(this);
            this.handleSubmit = this.handleSubmit.bind(this);
    }
    updateCommentFormState() {
            this.setState({
              isModalOpen: !this.state.isModalOpen
            });
          }

     handleSubmit(values) {
            this.updateCommentFormState();
            this.props.postComment(this.props.dishId, values.rating, values.author, values.comment);
        }
    render(){
        return(
        <React.Fragment>
        <Nav className="ml-auto" navbar>
                        <NavItem>
                            <Button outline onClick={()=>this.updateCommentFormState()} ><span className="fa fa-pencil fa-lg"></span> Submit Comment</Button>
                        </NavItem>
                    </Nav>
        <Modal isOpen={this.state.isModalOpen} toggle={this.updateCommentFormState}>
                          <ModalHeader toggle={this.updateCommentFormState}>Submit Comment</ModalHeader>
                          <ModalBody>
                                 <LocalForm onSubmit={(values) => this.handleSubmit(values)}>
                                    <Row className="form-group">
                                    <Col md={{size: 2, offset: 0}}>
                                  <Label htmlFor="rating">  Raiting</Label>
                                    </Col>
                                    </Row>
                                    <Row className="form-group">
                                        <Col md={{size: 12, offset: 0}}>
                                            <Control.select model=".rating" name="rating"
                                            className="form-control" id="rating">
                                                <option>1</option>
                                                <option>2</option>
                                                <option>3</option>
                                                <option>4</option>
                                                <option>5</option>
                                            </Control.select>
                                        </Col>
                                    </Row>
                                    <Row className="form-group">
                                    <Col md={{size: 5, offset: 0}}>
                                        <Label htmlFor="author" >Your Name</Label>
                                    </Col>
                                    </Row>

                                    <Row className="form-group">
                                        <Col md={12}>
                                            <Control.text  model=".author" id="author" name="author"
                                                placeholder="Your Name"
                                                className="form-control"
                                                validators={{
                                                    required,
                                                    minLength: minLength(3),
                                                    maxLength: maxLength(15)
                                                    }}
                                                />
                                                <Errors
                                                    className="text-danger"
                                                    model = ".author"
                                                    show="touched"
                                                    messages ={{
                                                        required: 'Required;',
                                                        minLength: 'Must be greater than 2 characters',
                                                        maxLength: 'Must be 15 characters or less'
                                                    }}
                                                />
                                        </Col>
                                    </Row>
                                    <Row className="form-group">
                                    <Col md={{size: 5, offset: 0}}>
                                        <Label htmlFor="comment" >Comment</Label>
                                    </Col>
                                    </Row>
                                    <Row className="form-group">
                                        <Col md={12}>
                                            <Control.textarea model=".comment" id="comment" name="comment"
                                                rows="6"
                                                className="form-control"/>
                                        </Col>
                                    </Row>
                                    <Row className="form-group">
                                        <Col md={{size: 10, offset: 0}}>
                                            <Button type="submit" color="primary">
                                                Submit
                                            </Button>
                                        </Col>
                                    </Row>
                                </LocalForm>
                          </ModalBody>
                      </Modal>
        </React.Fragment>
        );
    }
}

export default DishDetail;