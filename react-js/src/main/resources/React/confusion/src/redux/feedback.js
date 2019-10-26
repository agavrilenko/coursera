import * as ActionTypes from './ActionTypes';


export const Feedback = (state = { errMess: null, feedback:[]}, action) => {
  switch (action.type) {
    case ActionTypes.ADD_FEEDBACK:
      return {...state, errMess: null, comments: action.payload};

    case ActionTypes.FEEDBACK_FAILED:
      return {...state, errMess: action.payload};

    case ActionTypes.ADD_FEEDBACKS:
        var comment = action.payload;
        return { ...state, comments: state.feedback.concat(comment)};

    default:
      return state;
  }
};